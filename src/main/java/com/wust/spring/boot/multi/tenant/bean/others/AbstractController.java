package com.wust.spring.boot.multi.tenant.bean.others;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public abstract class AbstractController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    protected String getUser() {
        String username = UserContextHolder.getInstance().getLoginName();
        return username;
    }

    protected String getUserName() {
        return UserContextHolder.getInstance().getLoginName();
    }

    protected String getUserEmail() {
        return UserContextHolder.getInstance().getUserEmail();
    }

}
