package com.ayuan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${ayuan.info.base-package}
@SpringBootApplication(scanBasePackages = {"${ayuan.info.base-package}.server", "${ayuan.info.base-package}.module"})
public class AYuanServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AYuanServerApplication.class, args);
    }
}
