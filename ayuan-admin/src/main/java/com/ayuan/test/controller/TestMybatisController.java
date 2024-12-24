package com.ayuan.test.controller;

import com.ayuan.test.model.entity.TestMybatis;
import com.ayuan.test.service.TestMybatisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("testMybatis")
public class TestMybatisController {
    /**
     * 服务对象
     */
    @Resource
    private TestMybatisService testMybatisService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public TestMybatis selectOne() {
        return this.testMybatisService.queryById(1);
    }

}

