package com.cmri.um.he.index.mouth.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.mouth.entity.AppEmotionAnalyzeEntity;
import com.cmri.um.he.index.mouth.serivice.AppEmotionAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 	口碑指数原始数据录入
 * @author shihao
 * Created on 2018/8/14
 */
@RestController
@CrossOrigin
public class AppEmotionAnalyzeController extends ZRestController {


    @Autowired
    private AppEmotionAnalyzeService appEmotionAnalyzeService;

    @RequestMapping(value = "app-mouth",method = RequestMethod.POST)
    public ResponseMessage saveMouth(@RequestBody Map<String , List<AppEmotionAnalyzeEntity>> map){
        List<AppEmotionAnalyzeEntity> list = map.get("data");
        Boolean b= appEmotionAnalyzeService.saveMouth(list);
        Boolean flag = appEmotionAnalyzeService.getEmotionScore();
        ResponseMessage responseMessage =this.genResponseMessage();
        if(b&&flag){
            responseMessage.setMsg("添加成功");
        }else {
            responseMessage.setMsg("添加失败");
        }
       return  responseMessage;
    }

    @RequestMapping(value = "app-emotion",method = RequestMethod.GET)
    public ResponseMessage getEmotion(){
        Boolean flag = appEmotionAnalyzeService.getEmotionScore();
        ResponseMessage responseMessage =this.genResponseMessage();
        if(flag){
            responseMessage.setMsg("计算成功");
        }else {
            responseMessage.setMsg("计算失败");
        }
        return  responseMessage;
    }

    @RequestMapping(value = "app-he",method = RequestMethod.GET)
    public String getHe(){

        return  "hello world";
    }
}
