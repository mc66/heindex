package com.cmri.um.he.index.monument.controller;


import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.monument.service.AppBereavementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 情感指数变化趋势
 *
 * @author yinjunjun
 * Created on 2018/8/10
 */
@RestController
@RequestMapping("/app-bereavement")
@CrossOrigin
public class AppBereavementController extends ZRestController{
    @Autowired
    private AppBereavementService service;

    @RequestMapping(value = "/get-bereavement",method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam Integer category, String startTime,@RequestParam String endTime){
        List<Map<String, Object>> bereavement = null;
        if (startTime.equals("null")){
            try {
                String time = DefaultTime.getDefaultTimes(Constants.MONTH,5,endTime);
                bereavement = service.findBereavement(category, time, endTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            bereavement = service.findBereavement(category, startTime, endTime);
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",bereavement);
        return responseMessage;
    }

    @RequestMapping(value = "/get-MoonEmotion",method = RequestMethod.GET)
    public ResponseMessage findMoonEmotion(@RequestParam Integer category,@RequestParam String endTime) {
        List<Map<String, Object>> moonEmotion = service.findMoonEmotion(category, endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items", moonEmotion);
        return responseMessage;
    }
    @RequestMapping(value = "/get-number_comments",method = RequestMethod.GET)
    public ResponseMessage getNumberComments(@RequestParam Integer category,@RequestParam String startTime,@RequestParam String endTime){
        List<Map<String, Object>> appname = service.findAppName(category, startTime,endTime);
        List<Map<String, Object>> number_commentsJust = service.findNumberCommentsJust(category, startTime,endTime);
        List<Map<String, Object>> number_commentsCentre = service.findNumberCommentsCentre(category, startTime,endTime);
        List<Map<String, Object>> number_commentsLoad = service.findNumberCommentsLoad(category, startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("name",appname);
        responseMessage.set("positive ",number_commentsJust);
        responseMessage.set("neutral ",number_commentsCentre);
        responseMessage.set("negativity ",number_commentsLoad);
        return responseMessage;
    }

}
