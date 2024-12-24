package com.ayuan.core.model.dao;

import com.ayuan.core.model.entity.SysOperationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作日志 数据层
 *
 * @author aYuan
 */
@Mapper
public interface SysOperationLogMapper {

    /**
     * 新增操作日志
     *
     * @param operationLog 操作日志对象
     */
    public void insertOperationLog(SysOperationLog operationLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operationLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperationLog> selectOperationLogList(SysOperationLog operationLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteOperationLogByIds(String[] ids);

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    public SysOperationLog selectOperationLogById(Long id);

    /**
     * 清空操作日志
     */
    public void cleanOperationLog();
}
