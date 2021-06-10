package com.wust.spring.boot.multi.tenant.bean.service;


import com.wust.spring.boot.multi.tenant.bean.model.Tenant;

import java.util.List;

public interface TenantService {
    Tenant addTenant(Tenant tenant);

    Tenant getTenant(String tenantName);

    List<Tenant> getTenantList();
}
