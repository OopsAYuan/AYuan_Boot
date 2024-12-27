package com.ayuan.module.system.service.test;

import com.ayuan.module.system.dal.dataobject.test.TestMybatisDO;
import com.ayuan.module.system.mapper.mysql.test.TestMybatisMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ITestMybatisServiceImpl implements ITestMybatisService {

    @Resource
    private TestMybatisMapper testMybatisMapper;

    @Override
    public TestMybatisDO getUserByUsername(String name) {
        return testMybatisMapper.selectByUsername(name);
    }
}
