package com.cmri.um.he.index.login.service.impl;

import com.cmri.um.he.index.login.dao.AppLoginDao;
import com.cmri.um.he.index.login.entity.AppUserEntity;
import com.cmri.um.he.index.login.service.AppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 登陆页面
 * @author limin
 * Created on 2018/8/13
 */
@Service
public class AppLoginServiceImpl implements AppLoginService {

    @Autowired
    private AppLoginDao appLoginDao;

    /**
     *  登陆页面
     * @param username
     * @param userpass
     * @param request
     * @param response
     * @return
     */
    @Override
    public String getlogin(String username, String userpass, HttpServletRequest request,HttpServletResponse response) {

       List<AppUserEntity> list = appLoginDao.queryByName(username);

        if (list.size()==1){
            List<AppUserEntity> list1 = appLoginDao.getlogin(username,userpass);
            if(list1.size()==1){
                request.getSession().setAttribute("id",list1.get(0).getId());
                try {
                    response.setHeader("Access-Control-Allow-Credentials","true");
                    response.setHeader("Access-Control-Allow-Origin","http://localhost:9280");
                    response.setHeader("Cookie","token=cowshield");
                    //将验证信息保存到Cookie
                    Cookie cid=new Cookie("userid", UUID.randomUUID().toString().replaceAll("-", ""));
                    cid.setPath("/");
                    cid.setMaxAge(600);

                    cid.setHttpOnly(false);
                    response.addCookie(cid);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "loginYes";
            }
            return "loginNo";
        }
        return "nameNo";
    }
}
