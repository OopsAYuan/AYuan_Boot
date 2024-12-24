package com.ayuan.test.service;

import com.ayuan.test.model.entity.TestMybatis;

public interface TestMybatisService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestMybatis queryById(Integer id);


}
