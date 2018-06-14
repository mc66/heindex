package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 体验指数，当前是根据期数查询当前应用类所有应用的运营数据
 *
 * @author machao
 * Created on 2018/6/13
 */
@RestController
@RequestMapping("/app-operations-list")
public class AppOperationsListController extends ZRestController {

    @Autowired
    private AppOperationsService appOperationsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(
            @RequestParam String month,
            String category,
            @RequestParam Integer page,
            @RequestParam Integer step,
            HttpServletRequest request,
            HttpServletResponse response) {

        //以下三行response.set..是为了解决跨域访问拒绝的问题一定要写
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 响应类型
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");

        return appOperationsService.getOperationsList(month, category, page, step).updateResponse(genResponseMessage());


    }
}
