package com.wust.spring.boot.multi.tenant.bean.controller;

import com.wust.spring.boot.multi.tenant.bean.annotation.Api_Business;
import com.wust.spring.boot.multi.tenant.bean.context.ApiContext;
import com.wust.spring.boot.multi.tenant.bean.model.Tenant;
import com.wust.spring.boot.multi.tenant.bean.model.request.AccountRequest;
import com.wust.spring.boot.multi.tenant.bean.model.response.AccountResponse;
import com.wust.spring.boot.multi.tenant.bean.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
@Api_Business
@Api(value = "账户 - account账户api", tags = "账户 - account账户api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String getTest() {
        return "helloService.getTest()";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public AccountResponse createAccount(@RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }


}
