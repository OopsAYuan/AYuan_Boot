package com.ayuan.core.service.impl;


import com.ayuan.core.model.dao.SysOperationLogMapper;
import com.ayuan.core.model.entity.SysOperationLog;
import com.ayuan.core.service.ISysOperationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysOperationLogServiceImpl implements ISysOperationLogService {

    @Resource
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public void addOperationLog(SysOperationLog operationLog) {
        sysOperationLogMapper.insertOperationLog(operationLog);
    }

    @Override
    public List<SysOperationLog> getOperationLogList(SysOperationLog operationLog) {
        return sysOperationLogMapper.selectOperationLogList(operationLog);
    }

    @Override
    public int deleteOperationLogsByIds(String[] ids) {
        return sysOperationLogMapper.deleteOperationLogByIds(ids);
    }

    @Override
    public SysOperationLog getOperationLogById(Long id) {
        return sysOperationLogMapper.selectOperationLogById(id);
    }

    @Override
    public void cleanOperationLogs() {
        sysOperationLogMapper.cleanOperationLog();
    }
}
