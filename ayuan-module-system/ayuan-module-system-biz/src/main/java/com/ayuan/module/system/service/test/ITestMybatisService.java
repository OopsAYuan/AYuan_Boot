package com.ayuan.module.system.service.test;

import com.ayuan.module.system.dal.dataobject.test.TestMybatisDO;

public interface ITestMybatisService {

    /**
     * 通过用户名查询用户
     *
     * @param name 用户名
     * @return 用户对象信息
     */
    TestMybatisDO getUserByUsername(String name);

}
