--liquibase formatted sql

--changeset zhudayang:20210610-02
CREATE TABLE `accounts` (
    `id` bigint(20) unsigned NOT NULL COMMENT '分布式主id',
    `tenant_id` bigint(20) unsigned NOT NULL COMMENT '所属租户id',
    `owned_by_id` bigint(20) unsigned NOT NULL COMMENT '拥有者account id',
    `created_by_id` bigint(20) unsigned NOT NULL COMMENT '创建者account id',
    `created_time` datetime NOT NULL COMMENT '创建的UTC时间',
    `updated_by_id` bigint(20) unsigned DEFAULT NULL COMMENT '最后更新者account id',
    `updated_time` datetime DEFAULT NULL COMMENT '最后更新的UTC时间',
    `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '乐观锁版本控制字段',

    `login_name` varchar(128) NOT NULL COMMENT '登录名称',
    `description` varchar(512) NOT NULL DEFAULT '' COMMENT '描述',
    `status` varchar(32) NOT NULL COMMENT '状态',
    `account_type` varchar(32) NOT NULL COMMENT '类型',
    `effective_date` datetime DEFAULT NULL COMMENT '起效时间',
    `expiration_date` datetime DEFAULT NULL COMMENT '失效时间',
    `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',

    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenantId_loginName_accountType` (`tenant_id`, `login_name`, `account_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '平台帐号表';
