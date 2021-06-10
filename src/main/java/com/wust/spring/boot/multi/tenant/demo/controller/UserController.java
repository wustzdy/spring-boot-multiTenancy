package com.wust.spring.boot.multi.tenant.demo.controller;

import com.wust.spring.boot.multi.tenant.demo.model.User;
import com.wust.spring.boot.multi.tenant.demo.model.request.UserRequest;
import com.wust.spring.boot.multi.tenant.demo.annotation.Api_Business;
import com.wust.spring.boot.multi.tenant.demo.constant.page.PageResult;
import com.wust.spring.boot.multi.tenant.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("zdy/user")
@Api_Business
@Api(value = "用户 - user用户api", tags = "用户 - user用户api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String getTest() {
        return "helloService.getTest()";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUsers(@RequestParam String userName) {
        return userService.getUser(userName);
    }

    @ApiOperation(value = "查询iam用户分页列表信息", notes = "查询iam用户分页列表信息")
    @PostMapping("/queryPage")
    public PageResult<User> queryUserPage(@RequestBody(required = false) UserRequest request) {
        return userService.queryUserPage(request);
    }

    @ApiOperation(value = "自定义问题反馈列表分页", notes = "自定义问题反馈列表分页")
    @PostMapping("/_list")
    public PageResult<User> list(@RequestBody(required = false) UserRequest req) {
        return userService.list(req.getPageRequire(), req.getName());
    }
}
