package com.cmri.um.he.index.login.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.login.service.AppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登陆页面
 * @author limin
 * Created on 2018/8/13
 */
@RestController
@CrossOrigin
public class AppLoginController extends ZRestController {

    @Autowired
    private AppLoginService appLoginService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Object getlogin(@RequestParam String username, @RequestParam String userpass, HttpServletRequest request){
        String  login = appLoginService.getlogin(username,userpass,request);
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

}
