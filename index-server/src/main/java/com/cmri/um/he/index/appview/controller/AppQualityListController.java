package com.cmri.um.he.index.appview.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.appview.service.AppQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* 查询某类应用品质指数
* @author shihao
* Created on 2018/6/13
*/
@RestController
@RequestMapping("/app-quality-list")
@CrossOrigin
public class AppQualityListController extends ZRestController {
    @Autowired
    private AppQualityService appQualityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam String month, String category, @RequestParam Integer page, @RequestParam Integer step) {
          return appQualityService.find(month, category, page, step).updateResponse(genResponseMessage());

    }
}
