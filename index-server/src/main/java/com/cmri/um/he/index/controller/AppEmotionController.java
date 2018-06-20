package com.cmri.um.he.index.controller;


import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppEmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 口碑指数，当前年应用的情感得分
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@RestController
@RequestMapping("/app-emotion")
@CrossOrigin
public class AppEmotionController extends ZRestController{

    @Autowired
    private AppEmotionService appEmontionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam Integer category){
        List<Map<String, Object>> maps = appEmontionService.find(category);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("emotions",maps);
        return responseMessage;
    }

}
