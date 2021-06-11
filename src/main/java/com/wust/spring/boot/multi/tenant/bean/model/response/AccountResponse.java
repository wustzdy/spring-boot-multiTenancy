package com.wust.spring.boot.multi.tenant.bean.model.response;

import com.wust.spring.boot.multi.tenant.bean.contant.AccountStatus;
import com.wust.spring.boot.multi.tenant.bean.model.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Schema(name = "AccountResponse", description = "用户信息返回")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountResponse extends BaseModel {
    @Schema(name = "accountName", description = "用户名")
    private String accountName;
    @Schema(name = "status", description = "用户状态")
    private AccountStatus status;
    @Schema(name = "lastLoginTime", description = "用户最后登录时间")
    private Date lastLoginTime;
    @Schema(name = "createdTime", description = "用户创建时间")
    private Date createdTime;

}
