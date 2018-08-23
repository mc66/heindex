package com.cmri.um.he.index.login.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.common.ValidateCode;
import com.cmri.um.he.index.common.VerifyCodeUtils;
import com.cmri.um.he.index.login.service.AppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;



/**
 * 登陆页面
 * @author limin
 * Created on 2018/8/13
 */
@RestController
public class AppLoginController extends ZRestController {

    @Autowired
    private AppLoginService appLoginService;


    /**
     *   获取验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/getCode")
    @ResponseBody
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidateCode.getVerifyCode(request,response);
        /*VerifyCodeUtils.generateCode(request, response, 90, 4);*/
    }

    /**
     *   登陆页面
     * @param username
     * @param userpass
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Object getlogin(@RequestParam String username,@RequestParam String userpass,@RequestParam String userCode, HttpServletRequest request,HttpServletResponse response){
        Map map = new HashMap(16);
        String code =  (String) request.getSession().getAttribute("code");
        if (code.equals(userCode)){
            String  login = appLoginService.getlogin(username,userpass,request,response);
            if (login.equals("loginYes")){
                map.put("code",200);
                map.put("msg","登陆成功");
                map.put("succeed",true );
            }else if(login.equals("loginNo")){
                map.put("code",500);
                map.put("msg","登陆失败");
                map.put("succeed",false );
            }else {
                map.put("msg","用户名不存在");
            }
            return  map;
        }else {
            map.put("msg","验证码错误");
            return  map;
        }
    }

}
