package com.wust.spring.boot.multi.tenant.bean.contant;

public interface TenantMessageCodes {
    String SERVER_ERROR = "tenant.error.server_error";
    String CREATE_TABLE_FAILED = "iam.error.create_table_failed";
    String EXECUTION_IS_ERROR = "base.error.execution_is_error";
    String EXTRAS_PROPERTY_READ_ERROR = "base.error.extras_properties_read_error";
}
