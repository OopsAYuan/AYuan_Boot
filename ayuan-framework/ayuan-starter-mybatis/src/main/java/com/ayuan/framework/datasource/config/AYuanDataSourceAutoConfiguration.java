package com.ayuan.framework.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@AutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true) // 启动事务管理
@EnableConfigurationProperties(DruidStatProperties.class)
public class AYuanDataSourceAutoConfiguration {

    // TODO 去除广告的过滤器

}
