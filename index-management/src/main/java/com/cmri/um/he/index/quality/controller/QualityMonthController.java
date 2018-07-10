package com.cmri.um.he.index.quality.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.quality.service.QualityMonthService;
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
@RequestMapping("/app-qualityMonth")
@CrossOrigin
public class QualityMonthController extends ZRestController {
    @Autowired
    private QualityMonthService qualityMonthService;

    /**
     * 查询所有报告期数
     * */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(){
        List<Map<String, Object>> maps = qualityMonthService.findMonths();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",maps);
        return responseMessage;
    }
}
