package com.cmri.um.he.index.login.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.common.ValidateCode;
import com.cmri.um.he.index.login.service.AppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Object getlogin(@RequestParam String username, @RequestParam String userpass, HttpServletRequest request, HttpServletResponse response){
        String  login = appLoginService.getlogin(username,userpass,request);

        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin","http://223.105.1.80");
        response.setHeader("Set-Cookie","token=cowshield");
        Cookie cookie =new Cookie("test",login);
        response.addCookie(cookie);
        Map map = new HashMap(16);
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
    }
    @RequestMapping(value = "code",method = RequestMethod.GET)
     public void getCode(HttpServletRequest request, HttpServletResponse response){

        ValidateCode.getVerifyCode(request,response);
    };

    @RequestMapping(value = "app-test",method = RequestMethod.POST)
    public ResponseMessage getTest(@RequestParam String username, @RequestParam String password,HttpServletRequest request, HttpServletResponse response)throws Exception{

        username=URLDecoder.decode(username,"UTF-8");
        password=URLDecoder.decode(password,"UTF-8");
        Cookie cookie1 =new Cookie("username",username);
        Cookie cookie2 =new Cookie("password",password);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", "*");
       // response.setHeader("Access-Control-Allow-Origin: http://www.xxx.com");
        ResponseMessage requestMessage =this.genResponseMessage();
        requestMessage.setMsg("成功");
        return  requestMessage;

    };
}
