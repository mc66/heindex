package com.cmri.um.he.index.login.service;

import javax.servlet.http.HttpServletResponse; /**
 * 登陆页面
 * @author limin
 * Created on 2018/8/13
 */
public interface AppLoginService {

    boolean getlogin(String username, String userpass, HttpServletResponse response);
}
