package com.ayuan.test.controller;

import com.ayuan.common.annotation.Log;
import com.ayuan.common.enums.BusinessType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("log")
public class TestLogAnnotationController {

    @Log(title = "测试@Log注解", businessType = BusinessType.OTHER)
    @GetMapping("/hello")
    public String HelloLog(@RequestParam String name, @RequestParam String password) {
        if (Objects.equals(name, "admin")) {
            throw new RuntimeException("抛出异常");
        }
        return "测试@Log注解，记录操作日志";
    }
}
