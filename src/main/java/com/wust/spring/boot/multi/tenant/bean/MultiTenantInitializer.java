package com.wust.spring.boot.multi.tenant.bean;

import com.wust.spring.boot.multi.tenant.bean.api.ExecutionContextProvider;
import com.wust.spring.boot.multi.tenant.bean.contant.IamConstants;
import com.wust.spring.boot.multi.tenant.bean.context.CallerContext;
import com.wust.spring.boot.multi.tenant.bean.model.Tenant;
import com.wust.spring.boot.multi.tenant.bean.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MultiTenantInitializer {
    @Value("${tenant.core.MultiTenantInitializer.bootstrap:true}")
    private boolean bootstrap;
    @Autowired
    private ExecutionContextProvider executionContextProvider;
    @Autowired
    private TenantService tenantService;

    @PostConstruct
    private void init() throws Exception {
        if (!bootstrap) {
            return;
        }

        bindTenantContext(IamConstants.SYSTEM_TENANT_NAME);
        try {

        } finally {
            executionContextProvider.unbind();
        }

        bindTenantContext(IamConstants.DEFAULT_TENANT_NAME);
        try {

        } finally {
            executionContextProvider.unbind();
        }
    }

    private void bindTenantContext(String tenantName) {
        executionContextProvider.bind();
        CallerContext callerContext = new CallerContext();
        executionContextProvider.current().put(CallerContext.CONTEXT_KEY, callerContext);

        Tenant tenant = tenantService.getTenantByName(tenantName);
        if (tenant == null) {
            tenant = initTenantByName(tenantName);
        }
        callerContext.setTenantId(tenant.getId());
    }

    @Transactional
    public Tenant initTenantByName(String tenantName) {
        executionContextProvider.bind();

        try {
//            long rootId = idGenerator.nextId();
            long rootId = 100l;

            CallerContext callerContext = new CallerContext();
            callerContext.setCallerId(rootId);
            executionContextProvider.current().put(CallerContext.CONTEXT_KEY, callerContext);

            Tenant tenant = tenantService.createTenant(tenantName);
            callerContext.setTenantId(tenant.getId());

            return tenant;
        } finally {
            executionContextProvider.unbind();
        }
    }


}
