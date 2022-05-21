package com.wust.spring.boot.multi.tenant.bean.others.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wust.spring.boot.multi.tenant.bean.others.IMap;
import com.wust.spring.boot.multi.tenant.bean.others.JwtUtils;
import com.wust.spring.boot.multi.tenant.bean.others.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import static com.wust.spring.boot.multi.tenant.bean.others.BasicCloudConstants.X_CLIENT_TOKEN_USER;
import static com.wust.spring.boot.multi.tenant.bean.others.BasicCloudConstants.X_CLIENT_TOKEN_USER_ID;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从网关获取并校验,通过校验就可信任x-client-token-user中的信息
        checkToken(request, response);
        String userInfoString = StringUtils.defaultIfBlank(request.getHeader(X_CLIENT_TOKEN_USER), "{}");
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(userInfoString, IMap.class));
        MDC.put(X_CLIENT_TOKEN_USER_ID, UserContextHolder.getInstance().getUsername());

        log.info("---preHandle----end:{}");
        return true;
    }


    private void checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader(jwtUtils.getHeader());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(jwtUtils.getHeader());
        }
        if (jwtUtils.getClaimByToken(token) == null) {
            throw new RuntimeException("invalid token");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        MDC.clear();
        UserContextHolder.getInstance().clear();
    }
}
