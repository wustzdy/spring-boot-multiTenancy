package com.wust.spring.boot.multi.tenant.bean.others;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class ApiTestController extends AbstractController {
    @GetMapping
    public String test(@RequestParam("userName") String userName) {
        /*String user = getUser();
        if (user.equals(userName)) {
            return "true";
        }*/
        return "false";

    }
}
