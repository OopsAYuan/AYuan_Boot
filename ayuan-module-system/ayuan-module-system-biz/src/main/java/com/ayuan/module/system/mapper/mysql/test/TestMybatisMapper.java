package com.ayuan.module.system.mapper.mysql.test;

import com.ayuan.framework.mybatis.core.mapper.BaseMapperX;
import com.ayuan.module.system.dal.dataobject.test.TestMybatisDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMybatisMapper extends BaseMapperX<TestMybatisDO> {

    default TestMybatisDO selectByUsername(String username) {
        return selectOne(TestMybatisDO::getName, username);
    }

}
