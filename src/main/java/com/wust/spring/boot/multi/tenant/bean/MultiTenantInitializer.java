package com.wust.spring.boot.multi.tenant.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MultiTenantInitializer {
    @Value("${iam.core.IamInitializer.bootstrap:true}")
    private boolean bootstrap;

    @PostConstruct
    private void init() throws Exception {
        System.out.println("-------PostConstruct----------");
        if (!bootstrap) {
            return;
        }
    }
}
