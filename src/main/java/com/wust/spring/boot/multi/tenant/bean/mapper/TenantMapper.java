package com.wust.spring.boot.multi.tenant.bean.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wust.spring.boot.multi.tenant.bean.entity.TenantEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface TenantMapper extends BaseMapper<TenantEntity> {
    @Select("SELECT * FROM " + TenantEntity.TABLE_NAME + " WHERE name=#{TenantName} ")
    TenantEntity selectByName(@Param("TenantName") String TenantName);

    @SqlParser(filter = true)
    @Select("SELECT * FROM " + TenantEntity.TABLE_NAME + " WHERE name=#{name}")
    TenantEntity selectByTenantName(@Param("name") String name);
}
