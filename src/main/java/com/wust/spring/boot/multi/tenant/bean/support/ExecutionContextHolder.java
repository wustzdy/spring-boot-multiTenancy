package com.wust.spring.boot.multi.tenant.bean.support;


import com.wust.spring.boot.multi.tenant.bean.contant.TenantMessageCodes;
import com.wust.spring.boot.multi.tenant.bean.context.ExecutionContext;
import com.wust.spring.boot.multi.tenant.bean.error.TenantException;
import org.springframework.core.NamedInheritableThreadLocal;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.Nullable;

public abstract class ExecutionContextHolder {
    private static final ThreadLocal<ExecutionContext> holder = new NamedThreadLocal<>("ExecutionContext holder");
    private static final ThreadLocal<ExecutionContext> inheritableHolder =
            new NamedInheritableThreadLocal<>("ExecutionContext inheritable holder");

    public static void reset() {
        ExecutionContext ec = get();
        if (ec == null || ec.parent() == null) {
            holder.remove();
            inheritableHolder.remove();
        } else {
            set(ec.parent());
        }
    }

    public static void set(@Nullable ExecutionContext ec) {
        set(ec, true);
    }

    public static void set(@Nullable ExecutionContext ec, boolean inheritable) {
        if (ec == null) {
            reset();
            return;
        }

        if (inheritable) {
            inheritableHolder.set(ec);
            holder.remove();
        } else {
            holder.set(ec);
            inheritableHolder.remove();
        }
    }

    @Nullable
    public static ExecutionContext get() {
        ExecutionContext ec = holder.get();
        return ec != null ? ec : inheritableHolder.get();
    }

    public static ExecutionContext current() {
        return current(false, true);
    }

    public static ExecutionContext current(boolean createIfNessessary) {
        return current(createIfNessessary, true);
    }

    public static ExecutionContext current(boolean createIfNessessary, boolean createInheritable) {
        ExecutionContext ec = get();
        if (ec != null) {
            return ec;
        }

        if (createIfNessessary) {
            ec = new ExecutionContext();
            set(ec, createInheritable);
            return ec;
        }

        // TODO: update exception
        throw new TenantException(TenantMessageCodes.EXECUTION_IS_ERROR);
    }
}
