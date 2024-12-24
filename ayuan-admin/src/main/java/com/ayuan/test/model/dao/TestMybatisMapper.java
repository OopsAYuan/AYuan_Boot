package com.ayuan.test.model.dao;

import com.ayuan.test.model.entity.TestMybatis;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMybatisMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestMybatis queryById(Integer id);

}
