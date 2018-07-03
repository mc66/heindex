package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查询单个应用品质数据
 * @author shihao
 * Created on 2018/7/2
 */
@RestController
@RequestMapping("/app-quality")
@CrossOrigin
public class AppQualityController extends ZRestController {

    @Autowired
    private AppQualityService appQualityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage getQuliatyData(@RequestParam Integer app,@RequestParam String month){
        List resultList= appQualityService.getQualityData(app,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("quality",resultList);
        return responseMessage;
    }
}
