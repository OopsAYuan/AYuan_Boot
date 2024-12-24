package com.ayuan.core.manager.factory;

import com.ayuan.common.utils.AddressUtils;
import com.ayuan.common.utils.spring.SpringUtils;
import com.ayuan.core.model.entity.SysOperationLog;
import com.ayuan.core.service.ISysOperationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author liuhulu
 *
 */
public class AsyncFactory
{
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * TODO 同步session到数据库
     *
     * @param session 在线用户会话
     * @return 任务task
     */


    /**
     * 操作日志记录
     *
     * @param operationLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOperation(final SysOperationLog operationLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                operationLog.setOperationLocation(AddressUtils.getRealAddressByIP(operationLog.getOperationIp()));
                SpringUtils.getBean(ISysOperationLogService.class).addOperationLog(operationLog);
            }
        };
    }

    /**
     * TODO 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */

}
