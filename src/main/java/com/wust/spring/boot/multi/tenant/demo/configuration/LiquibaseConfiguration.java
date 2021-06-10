package com.wust.spring.boot.multi.tenant.demo.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        //指定changelog的位置，这里使用的一个master文件引用其他文件的方式
        liquibase.setChangeLog("classpath:user-changelog.xml");
        // liquibase.setContexts(liquibaseProperties.getContexts());
        //如果设置为true：第一次执行不会报错，第二次将会报错，导致程序无法启动，所以第一次执行完后一定要改为：false
//        liquibase.setShouldRun(false);
        return liquibase;
    }

}
