--liquibase formatted sql

--changeset zhudayang:20210610-01
CREATE TABLE `sys_tenant` (
    `id` int (11) NOT NULL AUTO_INCREMENT,
    `name` varchar(64) NOT NULL COMMENT '租户名称',
    `description` varchar(512) NOT NULL DEFAULT '' COMMENT '描述',
    `status` varchar(32) NOT NULL COMMENT '状态',
    `tenant_id` int(0) COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE =InnoDB DEFAULT CHARSET =utf8mb4 COLLATE =utf8mb4_unicode_ci COMMENT '租户表';
