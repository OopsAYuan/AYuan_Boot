package com.ayuan.core.aspectj;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.ayuan.common.annotation.Log;
import com.ayuan.common.enums.BusinessStatus;
import com.ayuan.common.utils.ServletUtils;
import com.ayuan.common.utils.StringUtils;
import com.ayuan.core.manager.AsyncManager;
import com.ayuan.core.manager.factory.AsyncFactory;
import com.ayuan.core.model.entity.SysOperationLog;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author aYuan
 */
@Aspect
@Component
public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /** 排除敏感属性字段 */
    public static final String[] EXCLUDE_PROPERTIES = { "password", "oldPassword", "newPassword", "confirmPassword" };

    /** 计算操作消耗时间 */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(controllerLog)")
    public void boBefore(JoinPoint joinPoint, Log controllerLog)
    {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult)
    {
        logClientInteraction(joinPoint, controllerLog);
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e)
    {
        logClientInteraction(joinPoint, controllerLog);
        // TODO 全局异常处理日志，同一异常处理
        // 获取类名和方法名
        String classMethod = joinPoint.getSignature().toShortString();

        // 提取异常的栈帧信息
        StackTraceElement[] stackTrace = e.getStackTrace();
        String location = "Unknown";
        if (stackTrace.length > 0) {
            StackTraceElement firstElement = stackTrace[0];
            location = firstElement.toString(); // 包含类名和行号
        }

        // 构建简化的日志信息
        log.error("{} at {}", e.getMessage(), location);
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult)
    {
        try
        {
            // TODO 获取当前的用户


            // *========数据库日志=========*//
            SysOperationLog operationLog = new SysOperationLog();
            operationLog.setOperationStatus(BusinessStatus.SUCCESS.ordinal());
            // TODO 请求的IP和地址 先用工具类，后期改为调用用户
            operationLog.setOperationIp(ServletUtils.getRequest().getRemoteAddr());
            operationLog.setOperationUrl(StringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, 255));
            operationLog.setOperationName("aYuan");

            if (e != null)
            {
                operationLog.setOperationStatus(BusinessStatus.FAIL.ordinal());
                operationLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operationLog.setClassMethod(className + "." + methodName + "()");
            // 设置请求方式
            operationLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operationLog, jsonResult);
            // 设置消耗时间
            operationLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOperation(operationLog));
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
        finally
        {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param operationLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperationLog operationLog, Object jsonResult) throws Exception
    {
        // 设置action动作
        operationLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operationLog.setTitle(log.title());
        // 设置操作人类别
        operationLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            operationLog.setOperationParam(getRequestValue(joinPoint, log.excludeParamNames()));
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && StringUtils.isNotNull(jsonResult))
        {
            operationLog.setJsonResult(StringUtils.substring(JSONObject.toJSONString(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数
     *
     */
    private String getRequestValue(JoinPoint joinPoint, String[] excludeParamNames)
    {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        if (StringUtils.isNotEmpty(map))
        {
            String params = JSONObject.toJSONString(map, excludePropertyPreFilter(excludeParamNames));
            return StringUtils.substring(params, 0, 2000);
        }
        else
        {
            Object args = joinPoint.getArgs();
            if (StringUtils.isNotNull(args))
            {
                String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
                return StringUtils.substring(params, 0, 2000);
            }
        }
        return  null;
    }

    /**
     * 忽略敏感属性
     */
    public PropertyPreFilters.MySimplePropertyPreFilter excludePropertyPreFilter(String[] excludeParamNames)
    {
        return new PropertyPreFilters().addFilter().addExcludes(ArrayUtils.addAll(EXCLUDE_PROPERTIES, excludeParamNames));
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (Object o : paramsArray)
            {
                if (StringUtils.isNotNull(o) && !isFilterObject(o))
                {
                    try
                    {
                        Object jsonObj = JSONObject.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
                        params += jsonObj.toString() + " ";
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Object value : collection)
            {
                return value instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Object value : map.entrySet())
            {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }

    /**
     * 记录客户端请求信息
     *
     * @param joinPoint 切点
     * @param controllerLog 操作日志
     */
    public void logClientInteraction(JoinPoint joinPoint, Log controllerLog) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        if (request != null) {
            log.info("URL: {}", request.getRequestURL().toString());
        }
        if (request != null) {
            log.info("HTTP_METHOD: {}", request.getMethod());
        }
        if (request != null) {
            log.info("IP: {}", request.getRemoteAddr());
        }
        log.info("CLASS_METHOD: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("ARGS: {}", getRequestValue(joinPoint, controllerLog.excludeParamNames()));
        if (request != null) {
            String header = request.getHeader("User-Agent");
            UserAgent browser = UserAgent.parseUserAgentString(header);
            //获取浏览器版本号
            Version version = browser.getBrowser().getVersion(request.getHeader("User-Agent"));
            //获取系统信息
            OperatingSystem os = browser.getOperatingSystem();
            if(browser.getBrowser()!= null && version != null){
                log.info("BROWSER/VERSION : {}/{}", browser.getBrowser().getName(), version.getVersion());
            }
            if(os != null){
                log.info("OS_NAME : {}", os.getName());
            }
        }
    }

}
