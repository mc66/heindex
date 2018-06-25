package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 体验指数，当前是应用的类别
 *
 * @author lch
 * Created on 2018/6/13
 */
@RestController
@RequestMapping("/app-category")
@CrossOrigin
public class AppCategoryController extends ZRestController {
    @Autowired
    private AppCategoryService appCategoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(HttpServletResponse response) {

        //以下三行response.set..是为了解决跨域访问拒绝的问题一定要写
        //response.setHeader("Access-Control-Allow-Origin", "*");
        // 响应类型
        //response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        // 响应头设置
        //response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");

        List resultList = appCategoryService.find();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("categorys",resultList);
        return responseMessage;
    }
}
