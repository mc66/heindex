package com.cmri.um.he.index.login.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.login.service.AppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseMessage getlogin(@RequestParam String username,@RequestParam String userpass){
        boolean n = appLoginService.getlogin(username,userpass);
        ResponseMessage responseMessage =this.genResponseMessage();
       if (n == true){
           responseMessage.setMsg("登陆成功");
       }else {
           responseMessage.setMsg("登陆失败");
       }
        return  responseMessage;
    }

}
