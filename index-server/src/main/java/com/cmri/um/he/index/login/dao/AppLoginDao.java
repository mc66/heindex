package com.cmri.um.he.index.login.dao;

import com.cmri.um.he.index.login.entity.AppUserEntity;
import com.cmri.um.he.index.login.mapper.AppLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登陆页面
 * @author limin
 * Created on 2018/8/13
 */
@Repository
public class AppLoginDao extends BaseDao{

    @Autowired
    private AppLoginMapper mapper;

    public List<AppUserEntity> getlogin(String username, String userpass) {
        return mapper.getlogin(username,userpass);
    }



}
