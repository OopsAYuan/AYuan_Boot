package com.ayuan.module.system.controller.test;

import com.ayuan.module.system.dal.dataobject.test.TestMybatisDO;
import com.ayuan.module.system.service.test.ITestMybatisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test/mybatis")
public class TestMybatisController {

    @Resource
    private ITestMybatisService testMybatisService;

    @GetMapping("/selectone")
    public TestMybatisDO selectOne() {
        return testMybatisService.getUserByUsername("aYuan");
    }
}
