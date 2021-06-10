package com.wust.spring.boot.multi.tenant.bean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wust.spring.boot.multi.tenant.bean.contant.AccountType;
import com.wust.spring.boot.multi.tenant.bean.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountMapper extends BaseMapper<AccountEntity> {
    @Select("SELECT * FROM " + AccountEntity.TABLE_NAME + " " +
            "WHERE owned_by_id=#{ownerId} AND account_type=#{type} AND status='NORMAL' " +
            "LIMIT 1")
    Optional<AccountEntity> findOneEnabledByOwned(
            @Param("ownerId") long ownerId,
            @Param("type") AccountType type);

    @Select("SELECT * FROM " + AccountEntity.TABLE_NAME + " " +
            "WHERE owned_by_id=#{ownerId} AND account_type=#{type} AND status='NORMAL' ")
    List<AccountEntity> findAllEnabledByOwned(
            @Param("ownerId") long ownerId,
            @Param("type") AccountType type);

    @Select("SELECT * FROM " + AccountEntity.TABLE_NAME + " WHERE status='NORMAL' ")
    List<AccountEntity> listAllEnabled();


    @Select("SELECT count(1) FROM " + AccountEntity.TABLE_NAME + " WHERE login_name=#{loginName} AND " +
            "account_type=#{type} AND status<>'DELETED' ")
    boolean exists(@Param("loginName") String loginName, @Param("type") AccountType type);

    @Update("UPDATE " + AccountEntity.TABLE_NAME + " SET last_login_time=#{lastLoginTime} WHERE id=#{id}")
    void updateLoginTimeById(@Param("id") long id, @Param("lastLoginTime") Date lastLoginTime);
}
