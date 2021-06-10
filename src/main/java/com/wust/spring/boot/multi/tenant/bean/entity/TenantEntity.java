package com.wust.spring.boot.multi.tenant.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wust.spring.boot.multi.tenant.bean.contant.TenantStatus;
import lombok.Data;

@Data
@TableName(TenantEntity.TABLE_NAME)
public class TenantEntity {
    public static final String TABLE_NAME = "tenant";
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "description")
    private String description;
    @TableField(value = "status")
    private TenantStatus status;
}
