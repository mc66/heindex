package com.cmri.um.he.index.login.service.impl;

import com.cmri.um.he.index.login.dao.AppLoginDao;
import com.cmri.um.he.index.login.entity.AppUserEntity;
import com.cmri.um.he.index.login.service.AppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import java.util.List;

/**
 * 登陆页面
 * @author limin
 * Created on 2018/8/13
 */
@Service
public class AppLoginServiceImpl implements AppLoginService {

    @Autowired
    private AppLoginDao appLoginDao;

    @Override
    public boolean getlogin(String username, String userpass, HttpServletResponse response) {

       List<AppUserEntity> list = appLoginDao.getlogin(username,userpass);

        if (list.size()==1){
            Cookie cookie = new Cookie("loginmsg","");
            cookie.setValue(list.get(0).getUsername());
            response.addCookie(cookie);
            return true;
        }
        return false;
    }
}
