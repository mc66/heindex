package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseMessage get() {
        List resultList = appCategoryService.find();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("categorys",resultList);
        return responseMessage;
    }
}
