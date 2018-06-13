package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseMessage get(@RequestParam String month, String category, @RequestParam Integer page, @RequestParam Integer step) {

            return appOperationsService.getOperationsList(month, category, page, step).updateResponse(genResponseMessage());

    }
}
