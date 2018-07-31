package com.cmri.um.he.index.appview.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.appview.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 体验指数，当前是应用的类别
 *
 * @author lch
 * Created on 2018/6/13
 */
@RestController
@CrossOrigin
public class AppCategoryController extends ZRestController {
    @Autowired
    private AppCategoryService appCategoryService;

    /**
     * 查询应用类
     * @return
     */
    @RequestMapping(value="/app-category",method = RequestMethod.GET)
    public ResponseMessage get() {
        List resultList = appCategoryService.find();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("category",resultList);
        return responseMessage;
    }

    /**
     * 查询移动app对应的应用类
     * @return
     */
    @RequestMapping(value="/app-category-nav",method = RequestMethod.GET)
    public ResponseMessage gets() {
        List resultList = appCategoryService.finds();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("categorys",resultList);
        return responseMessage;
    }

    /**
     * 根据应用类别查app
     * @param category
     * @return
     */
    @RequestMapping(value = "/app-findAppByCategory",method = RequestMethod.GET)
    public ResponseMessage findAppByCategory(@RequestParam Integer category) {
        List<Map<String, Object>> resultList = appCategoryService.findAppByCategory(category);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("apps",resultList);
        return responseMessage;
    }
}
