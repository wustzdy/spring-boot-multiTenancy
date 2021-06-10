package com.wust.spring.boot.multi.tenant.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wust.spring.boot.multi.tenant.bean.contant.TenantStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName(TenantEntity.TABLE_NAME)
@EqualsAndHashCode(callSuper = true)
public class TenantEntity extends BaseEntity<TenantEntity> {
    public static final String TABLE_NAME = "tenant";

    @TableField(value = "name")
    private String name;
    @TableField(value = "description")
    private String description;
    @TableField(value = "status")
    private TenantStatus status;
}
