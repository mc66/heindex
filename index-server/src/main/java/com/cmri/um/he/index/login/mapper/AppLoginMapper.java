package com.cmri.um.he.index.login.mapper;

import com.cmri.um.he.index.login.entity.AppUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppLoginMapper {

    @Select(" SELECT * FROM app_user au where au.username=#{username} and au.`password`=#{userpass} ")
    List<AppUserEntity> getlogin(@Param("username") String username, @Param("userpass") String userpass);
}
