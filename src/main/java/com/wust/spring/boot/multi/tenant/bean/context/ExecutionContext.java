package com.wust.spring.boot.multi.tenant.bean.context;

import java.util.HashMap;

public class ExecutionContext {
    private final ExecutionContext parent;
    private final HashMap<String, Object> map = new HashMap<>();

    public ExecutionContext() {
        this(null);
    }

    public ExecutionContext(ExecutionContext parent) {
        this.parent = parent;
    }

    public ExecutionContext parent() {
        return parent;
    }

    private Object getFromParent(String key) {
        if (parent == null) {
            return null;
        }
        return parent.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        Object value = null;
        if (map.containsKey(key)) {
            value = map.get(key);
        } else {
            value = getFromParent(key);
        }
        return (T)value;
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public void clear() {
        map.clear();
    }
}
