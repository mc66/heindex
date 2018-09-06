package com.cmri.um.he.index.appview.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.appview.service.AppOwnDescService;
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
public class AppOwnDescController extends ZRestController {

    @Autowired
    private AppOwnDescService service;

    /**
     * 咪咕视频所属类品质分数
     * @param id
     * @param month
     * @return
     */
    @RequestMapping(value = "/quality",method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam Integer id,@RequestParam String month){
        List<Map<String, Object>> appDesc = service.findByAppId(id, month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",appDesc);
        return responseMessage;
    }

    @RequestMapping(value = "/operations",method = RequestMethod.GET)
    public ResponseMessage getData(@RequestParam Integer id,@RequestParam String month){
        List<Map<String, Object>> appDesc = service.findByIdAndMonth(id, month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",appDesc);
        return responseMessage;
    }

    /**
     * 体验指数中产品品质和运营中的产品简介
     * @param id
     * @param month
     * @param flag
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResponseMessage getAppInfo(@RequestParam Integer id,@RequestParam String month,@RequestParam Integer flag){
        List<Map<String, Object>> appInfo = service.findAppInfoByIdAndMonth(id, month, flag);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("appInfo",appInfo);
        return responseMessage;
    }
}
