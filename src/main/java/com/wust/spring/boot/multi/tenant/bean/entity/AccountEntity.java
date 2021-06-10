package com.wust.spring.boot.multi.tenant.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wust.spring.boot.multi.tenant.bean.contant.AccountStatus;
import com.wust.spring.boot.multi.tenant.bean.contant.AccountType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName(AccountEntity.TABLE_NAME)
public class AccountEntity extends BaseEntity<AccountEntity> {
    public static final String TABLE_NAME = "accounts";

    @TableField
    private String loginName;
    @TableField
    private String description;
    @TableField
    private AccountStatus status;
    @TableField
    private AccountType accountType;
    @TableField
    private Date effectiveDate;
    @TableField
    private Date expirationDate;
    @TableField
    private Date lastLoginTime;
}
