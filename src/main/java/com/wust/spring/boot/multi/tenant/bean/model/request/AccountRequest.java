package com.wust.spring.boot.multi.tenant.bean.model.request;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Schema(name = "CreateAccountRequest", description = "创建用户请求")
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class AccountRequest {
    @Schema(name = "accountName", description = "用户名")
    @NotEmpty
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_]{1,20}$")
    private String accountName;
   /* @Schema(name = "emailAddress", description = "用户邮件地址")
    @NotEmpty
    @Email
    private String emailAddress;
    @Schema(name = "mobilePhone", description = "用户电话号码")
    @NotEmpty
    @Pattern(regexp = "^1[3|45789][0-9]{9}$")
    private String mobilePhone;
    @Schema(name = "description", description = "用户描述")
    @Size(max = 1000)
    private String description;
    @Hidden
    @Schema(description = "身份提供商", requiredProperties = {"IAM_IDENTITY", "SENSETIME_LDAP"})
    private String providerName;
    @Schema(name = "accountRoleId", description = "用户角色")
    @NotEmpty
    private String accountRoleId;
    @Schema(name = "accountPolicyId", description = "用户权限列表")
    private List<String> accountPolicyId;*/
}
