package com.wust.spring.boot.multi.tenant.bean.api;


import com.wust.spring.boot.multi.tenant.bean.context.ExecutionContext;

public interface ExecutionContextProvider {
    ExecutionContext current();

    void bind();

    void unbind();

    boolean hasContext();

    @SuppressWarnings("unchecked")
    default <T> T current(String key) {
        return (T) current().get(key);
    }
}
