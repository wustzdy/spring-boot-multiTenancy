package com.wust.spring.boot.multi.tenant.bean.context;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class CallerContext {
    public static String CONTEXT_KEY = "CallerContext";

    private Long callerId;
    private Long runAsId;
    private Long tenantId;
    private Long runAsTenantId;
    private Long credentialId;
    private Long sessionId;
    private Long workspaceId;
    private int ratePerSecond;
    private Map<String, String> tags;

    public Long currentUserId() {
        if (runAsId != null) {
            return runAsId;
        }

        return callerId;
    }

    public Long currentTenantId() {
        if (runAsTenantId != null) {
            return runAsTenantId;
        }

        return tenantId;
    }
}
