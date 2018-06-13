package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* 查询某类应用品质指数
* @author shihao
* Created on 2018/6/13
*/
@RestController
@RequestMapping("/app-quality-list")
public class AppQualityListController extends ZRestController {
    @Autowired
    private AppQualityService appQualityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam String month, String category, @RequestParam Integer page, @RequestParam Integer step) {
          return appQualityService.find(month, category, page, step).updateResponse(genResponseMessage());

    }
}
