package com.wust.spring.boot.multi.tenant.demo.context;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description 系统的上下文帮助类。ConcurrentHashMap设置租户ID，供后续的MP的getTenantId()取出
 * @Copyright:
 */
@Component
public class ApiContext {
    private static final String KEY_CURRENT_TENANT_ID = "KEY_CURRENT_TENANT_ID";
    private static final Map<String, Object> mContext = new ConcurrentHashMap<>();

    public void setCurrentTenantId(Long providerId) {
        mContext.put(KEY_CURRENT_TENANT_ID, providerId);
    }

    public Long getCurrentTenantId() {
        return (Long) mContext.get(KEY_CURRENT_TENANT_ID);
    }
}
