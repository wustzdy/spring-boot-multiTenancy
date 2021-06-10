package com.wust.spring.boot.multi.tenant.demo.service;

import com.wust.spring.boot.multi.tenant.demo.model.User;
import com.wust.spring.boot.multi.tenant.demo.model.request.UserRequest;
import com.wust.spring.boot.multi.tenant.demo.constant.page.PageRequire;
import com.wust.spring.boot.multi.tenant.demo.constant.page.PageResult;

public interface UserService {
    User addUser(User user);

    User getUser(String userName);

    public PageResult<User> queryUserPage(UserRequest request);

    public PageResult<User> list(PageRequire pageRequire, String name);
}
