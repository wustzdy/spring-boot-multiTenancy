package com.wust.spring.boot.multi.tenant.bean.model.request;

import com.wust.spring.boot.multi.tenant.bean.constant.page.PageRequire;
import lombok.Data;

@Data
public class UserRequest {
    private int id;
    private String name;
    private String address;
    private int age;

    private PageRequire pageRequire;
}
