package com.cmri.um.he.index.login.entity;

import java.io.Serializable;
/**
 * 登陆页面表的entity
 * @author limin
 * Created on 2018/8/13
 */
public class AppUserEntity implements Serializable{

    private Integer id;

    private String username;

    private String userpass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }
}
