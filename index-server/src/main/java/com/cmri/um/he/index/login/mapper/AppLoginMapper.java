package com.cmri.um.he.index.login.mapper;

import com.cmri.um.he.index.login.entity.AppUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppLoginMapper {

    /**
     *   验证用户名和密码
     * @param username
     * @param userpass
     * @return
     */
    @Select(" SELECT * FROM app_user au where au.username=#{username} and au.`password`=#{userpass} ")
    List<AppUserEntity> getlogin(@Param("username") String username, @Param("userpass") String userpass);

    /**
     *  验证用户名
     * @param username
     * @return
     */
    @Select(" SELECT * FROM app_user au where au.username=#{username} ")
    List<AppUserEntity> queryByName(@Param("username")String username);
}
