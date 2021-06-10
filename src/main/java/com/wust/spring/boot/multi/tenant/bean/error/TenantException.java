package com.wust.spring.boot.multi.tenant.bean.error;

public class TenantException extends BaseException {
    public TenantException(String code) {
        super(code);
    }

    public TenantException(String code, Object... args) {
        super(code, args);
    }

    public TenantException(Throwable cause) {
        super(cause);
    }

    public TenantException(Throwable cause, String code, Object... args) {
        super(cause, code, args);
    }
}
