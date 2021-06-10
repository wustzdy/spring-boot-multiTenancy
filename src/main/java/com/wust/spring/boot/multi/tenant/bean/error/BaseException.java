package com.wust.spring.boot.multi.tenant.bean.error;


import com.wust.spring.boot.multi.tenant.bean.contant.TenantMessageCodes;

import java.util.UUID;

public class BaseException extends RuntimeException {
    private static final String DEFAULT_CODE = TenantMessageCodes.SERVER_ERROR;

    private final String id = UUID.randomUUID().toString();
    private Object[] args = new Object[0];

    public String getId() {
        return id;
    }

    public Object[] getArgs() {
        return args;
    }

    public BaseException() {
        super(DEFAULT_CODE);
    }

    public BaseException(String code, Object... args) {
        super(code);
        this.args = args;
    }

    public BaseException(Throwable cause) {
        super(DEFAULT_CODE, cause);
    }

    public BaseException(Throwable cause, String code, Object... args) {
        super(code, cause);
        this.args = args;
    }
}
