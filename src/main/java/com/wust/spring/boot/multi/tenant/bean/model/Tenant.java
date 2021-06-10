package com.wust.spring.boot.multi.tenant.bean.model;

import com.wust.spring.boot.multi.tenant.bean.contant.TenantStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Tenant extends BaseModel {
    private String name;
    private String description;
    private TenantStatus status;
}
