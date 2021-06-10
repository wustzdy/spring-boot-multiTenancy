package com.wust.spring.boot.multi.tenant.demo.controller;

import com.wust.spring.boot.multi.tenant.demo.annotation.Api_Business;
import com.wust.spring.boot.multi.tenant.demo.context.ApiContext;
import com.wust.spring.boot.multi.tenant.demo.model.Tenant;
import com.wust.spring.boot.multi.tenant.demo.service.TenantService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("zdy/tenant")
@Api_Business
@Api(value = "租户 - tenant租户api", tags = "租户 - tenant租户api")
public class TenantController {

    @Autowired
    private TenantService tenantService;
    @Autowired
    private ApiContext apiContext;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String getTest() {
        return "helloService.getTest()";
    }

    @RequestMapping(value = "/createTenant", method = RequestMethod.POST)
    public Tenant createUser(@RequestBody Tenant tenant) {
        return tenantService.addTenant(tenant);
    }

    @RequestMapping(value = "/getTenant", method = RequestMethod.GET)
    public Tenant getUsers(@RequestParam String tenantName) {
        return tenantService.getTenant(tenantName);
    }

    @RequestMapping(value = "/setApiContextTenantId", method = RequestMethod.GET)
    public void setApiContextTenantId(@RequestParam("tenantId") Long tenantId) {
        apiContext.setCurrentTenantId(tenantId);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Tenant> tenantList() {
        return tenantService.getTenantList();
    }


}
