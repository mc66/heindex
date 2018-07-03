package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppOwnDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 咪咕视频介绍以及所属类品质分数
 *
 * @author yinjunjun
 * Created on 2018/07/02
 */
@RestController
@RequestMapping("app-desc")
@CrossOrigin
public class AppOwnDescController extends ZRestController {

    @Autowired
    private AppOwnDescService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam Integer id,@RequestParam String month){
        List<Map<String, Object>> appDesc = service.findByAppId(id, month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",appDesc);
        return responseMessage;
    }
}
