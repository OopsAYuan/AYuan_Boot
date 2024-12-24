package com.ayuan.core.model.entity;

import com.ayuan.core.model.BaseEntity;

import java.time.LocalDateTime;

/**
 * 操作日志记录表 sys_operation_log
 *
 * @author aYuan
 */
public class SysOperationLog extends BaseEntity {

    private Long id; // 日志主键id

    private String title; // 操作模块

    private Integer businessType; // 业务类型（0其它 1新增 2修改 3删除）

    private String classMethod; // 请求处理方法

    private String requestMethod; // 请求方式

    private Integer operatorType; // 操作类别（0其它 1后台用户 2手机端用户）

    private String operationName; // 操作人员

    private String operationUrl; // 请求url

    private String operationIp; // 操作ip

    private String operationLocation; // 操作地点

    private String operationParam; // 请求参数

    private String jsonResult; // 返回参数

    private Integer operationStatus; // 操作状态（0正常 1异常）

    private String errorMsg; // 错误消息

    private LocalDateTime operationTime; // 操作时间

    private Long costTime; // 消耗时间

    private String remark; // 备注

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationUrl() {
        return operationUrl;
    }

    public void setOperationUrl(String operationUrl) {
        this.operationUrl = operationUrl;
    }

    public String getOperationIp() {
        return operationIp;
    }

    public void setOperationIp(String operationIp) {
        this.operationIp = operationIp;
    }

    public String getOperationLocation() {
        return operationLocation;
    }

    public void setOperationLocation(String operationLocation) {
        this.operationLocation = operationLocation;
    }

    public String getOperationParam() {
        return operationParam;
    }

    public void setOperationParam(String operationParam) {
        this.operationParam = operationParam;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    public Integer getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(Integer operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }
}
