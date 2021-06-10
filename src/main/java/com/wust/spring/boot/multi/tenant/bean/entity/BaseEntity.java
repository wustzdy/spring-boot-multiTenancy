package com.wust.spring.boot.multi.tenant.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity<T extends BaseEntity<?>> extends Model<T> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private Long ownedById;
    @TableField(fill = FieldFill.INSERT)
    private Long createdById;
    @TableField(fill = FieldFill.UPDATE)
    private Long updatedById;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updatedTime;
    @Version
    private Long version;
}
