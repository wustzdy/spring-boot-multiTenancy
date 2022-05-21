package com.wust.spring.boot.multi.tenant;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan(basePackages = "com.wust.spring.boot.multi.tenant.bean.mapper")
@ComponentScan("com.wust.spring.boot.multi.tenant")
public class SpringBootMultiTenantApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultiTenantApplication.class, args);
    }
}
