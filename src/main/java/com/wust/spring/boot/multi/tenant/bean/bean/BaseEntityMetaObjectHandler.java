package com.wust.spring.boot.multi.tenant.bean.bean;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wust.spring.boot.multi.tenant.bean.api.Clock;
import com.wust.spring.boot.multi.tenant.bean.api.ExecutionContextProvider;
import com.wust.spring.boot.multi.tenant.bean.context.CallerContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseEntityMetaObjectHandler implements MetaObjectHandler {
    @Autowired
    private Clock clock;
    @Autowired
    private ExecutionContextProvider executionContextProvider;

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "createdTime", Date.class, clock.now());

        CallerContext callerContext = executionContextProvider.current().get(CallerContext.CONTEXT_KEY);
        strictInsertFill(metaObject, "createdById", Long.class, callerContext.getCallerId());
        strictInsertFill(metaObject, "ownedById", Long.class, callerContext.currentUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "updatedTime", Date.class, clock.now());

        CallerContext callerContext = executionContextProvider.current().get(CallerContext.CONTEXT_KEY);
        strictUpdateFill(metaObject, "updatedById", Long.class, callerContext.getCallerId());
    }
}
