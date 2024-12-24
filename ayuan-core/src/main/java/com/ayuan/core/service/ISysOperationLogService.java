package com.ayuan.core.service;

import com.ayuan.core.model.entity.SysOperationLog;

import java.util.List;

public interface ISysOperationLogService {

    /**
     * 新增操作日志
     *
     * @param operationLog 操作日志对象
     */
    void addOperationLog(SysOperationLog operationLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operationLog 操作日志对象
     * @return 操作日志集合
     */
    List<SysOperationLog> getOperationLogList(SysOperationLog operationLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteOperationLogsByIds(String[] ids);

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    SysOperationLog getOperationLogById(Long id);

    /**
     * 清空操作日志
     */
    void cleanOperationLogs();
}
