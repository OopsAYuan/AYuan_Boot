package com.ayuan.test.service.impl;

import com.ayuan.test.model.entity.TestMybatis;
import com.ayuan.test.model.dao.TestMybatisMapper;
import com.ayuan.test.service.TestMybatisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("testMybatisService")
public class TestMybatisServiceImpl implements TestMybatisService {
    @Resource
    private TestMybatisMapper testMybatisMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TestMybatis queryById(Integer id) {
        return this.testMybatisMapper.queryById(id);
    }

}
