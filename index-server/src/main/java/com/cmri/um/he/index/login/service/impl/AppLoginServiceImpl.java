package com.cmri.um.he.index.login.service.impl;

import com.cmri.um.he.index.login.dao.AppLoginDao;
import com.cmri.um.he.index.login.entity.AppUserEntity;
import com.cmri.um.he.index.login.service.AppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public String getlogin(String username, String userpass, HttpServletRequest response) {

       List<AppUserEntity> list = appLoginDao.queryByName(username);

        if (list.size()==1){
            List<AppUserEntity> list1 = appLoginDao.getlogin(username,userpass);
            if(list1.size()==1){
                response.getSession().setAttribute("loginuser",list1.get(0));
                return "loginYes";
            }
            return "loginNo";
        }
        return "nameNo";
    }
}
