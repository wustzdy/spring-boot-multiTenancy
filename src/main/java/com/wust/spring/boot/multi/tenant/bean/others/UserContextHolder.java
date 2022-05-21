package com.wust.spring.boot.multi.tenant.bean.others;

import java.util.Map;
import java.util.Optional;

public class UserContextHolder {
    private ThreadLocal<IMap> threadLocal;

    private UserContextHolder() {
        this.threadLocal = new ThreadLocal<>();
    }

    /**
     * 创建实例
     *
     * @return
     */
    public static UserContextHolder getInstance() {
        return SingletonHolder.S_INSTANCE;
    }

    /**
     * 获取上下文中的信息
     *
     * @return
     */
    public IMap getContext() {
        return Optional.ofNullable(threadLocal.get()).orElse(IMap.Builder.build().add("user_name", ""));
    }

    /**
     * 用户上下文中放入信息
     */
    public void setContext(IMap map) {
        threadLocal.set(map);
    }

    /**
     * 获取上下文中的用户名
     *
     * @return
     */
    public String getUsername() {
        return (String) Optional.ofNullable(threadLocal.get()).orElse(IMap.Builder.build()).get("user_name");
    }

    public String getLoginName() {
        return (String) Optional.ofNullable(threadLocal.get()).orElse(IMap.Builder.build()).get("login_name");
    }

    public String getUserEmail() {
        return (String) Optional.ofNullable(threadLocal.get()).orElse(IMap.Builder.build()).get("email");
    }

    /**
     * 清空上下文
     */
    public void clear() {
        threadLocal.remove();
    }

    /**
     * 静态内部类单例模式
     * 单例初使化
     */
    private static class SingletonHolder {
        private static final UserContextHolder S_INSTANCE = new UserContextHolder();
    }

}
