package com.wust.spring.boot.multi.tenant.demo.service;


import com.wust.spring.boot.multi.tenant.demo.model.Tenant;

public interface TenantService {
    Tenant addTenant(Tenant tenant);

    Tenant getTenant(String tenantName);
}
