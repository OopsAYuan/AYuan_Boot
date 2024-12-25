package com.ayuan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${ayuan.info.base-package}
// TODO 研究 ${yudao.info.base-package} 通配符
@SpringBootApplication(scanBasePackages = {"com.ayuan.module"})
public class AYuanServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AYuanServerApplication.class, args);
    }
}
