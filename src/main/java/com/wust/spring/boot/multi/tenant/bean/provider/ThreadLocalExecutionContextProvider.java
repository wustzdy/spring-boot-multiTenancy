package com.wust.spring.boot.multi.tenant.bean.provider;


import com.wust.spring.boot.multi.tenant.bean.api.ExecutionContextProvider;
import com.wust.spring.boot.multi.tenant.bean.context.ExecutionContext;
import com.wust.spring.boot.multi.tenant.bean.support.ExecutionContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalExecutionContextProvider implements ExecutionContextProvider {

    @Override
    public ExecutionContext current() {
        return ExecutionContextHolder.current();
    }

    @Override
    public void bind() {
        ExecutionContextHolder.set(new ExecutionContext(ExecutionContextHolder.get()));
    }

    @Override
    public void unbind() {
        ExecutionContextHolder.reset();
    }

    @Override
    public boolean hasContext() {
        return ExecutionContextHolder.get() != null;
    }
}
