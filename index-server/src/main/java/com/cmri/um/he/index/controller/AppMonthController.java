package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppEmotionService;
import com.cmri.um.he.index.service.AppMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 报告期数
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@RestController
@RequestMapping("/app-month")
@CrossOrigin
public class AppMonthController extends ZRestController {
    @Autowired
    private AppMonthService appMonthService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(){
        List<Map<String, Object>> maps = appMonthService.findMonths();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",maps);
        return responseMessage;
    }
}
