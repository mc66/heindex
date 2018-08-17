package com.cmri.um.he.index.appview.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.appview.service.AppMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 报告期数
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@RestController
@CrossOrigin
public class AppMonthController extends ZRestController {
    @Autowired
    private AppMonthService appMonthService;

    @RequestMapping(value = "/app-month",method = RequestMethod.GET)
    public ResponseMessage getMonth(){
        List<Map<String, Object>> maps = appMonthService.findMonths();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",maps);
        return responseMessage;
    }

    @RequestMapping(value = "/app-experience-month",method = RequestMethod.GET)
    public ResponseMessage getExperienceMonth(){
        List<Map<String, Object>> maps = appMonthService.findExperienceMonths();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",maps);
        return responseMessage;
    }
}
