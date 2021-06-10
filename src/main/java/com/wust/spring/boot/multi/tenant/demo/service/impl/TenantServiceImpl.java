package com.wust.spring.boot.multi.tenant.demo.service.impl;

import com.wust.spring.boot.multi.tenant.demo.entity.TenantEntity;
import com.wust.spring.boot.multi.tenant.demo.mapper.TenantMapper;
import com.wust.spring.boot.multi.tenant.demo.model.Tenant;
import com.wust.spring.boot.multi.tenant.demo.util.JacksonUtil;
import com.wust.spring.boot.multi.tenant.demo.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public Tenant addTenant(Tenant tenant) {
        log.info("创建tenant请求参数tenant：. user=[{}]", JacksonUtil.bean2Json(tenant));
        TenantEntity tenantEntity = new TenantEntity();
        BeanUtils.copyProperties(tenant, tenantEntity);
        if (tenantMapper.insert(tenantEntity) != 1) {
            throw new RuntimeException("error");
        }
        Tenant tenant1 = new Tenant();
        BeanUtils.copyProperties(tenantEntity, tenant1);
        log.info("创建tenant返回参数tenant1：. tenant1=[{}]", JacksonUtil.bean2Json(tenant1));
        return tenant1;
    }

    @Override
    public Tenant getTenant(String tenantName) {
        TenantEntity tenantEntity = tenantMapper.selectByName(tenantName);
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(tenantEntity, tenant);
        return tenant;
    }
}
