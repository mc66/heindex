package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 查询未计算体验指数的所有应用，计算应用的品质总分
 * @author machao
 * Created on 2018/6/28
 */
@RestController
@CrossOrigin
public class AppQualityController extends ZRestController {

    @Autowired
    AppQualityService appQualityService;

    /**
     * 查询所有未计算品质得分的应用
     * @return
     */
    @RequestMapping(value = "/app-quality-qindex-list",method = RequestMethod.GET)
    public ResponseMessage getQualityList() {

        List resultList = appQualityService.quary();
        ResponseMessage responseMessage = this.genResponseMessage();
        if(resultList!=null && resultList.size()!=0){
            responseMessage.set("qualityList",resultList);
        }else {
            responseMessage.setMsg("没有需要处理数据！！！");
        }
        return responseMessage;
    }

    /**
     * 计算应用的品质得分并存入数据库
     * @return
     */
    @RequestMapping(value = "/app-quality-qindex",method = RequestMethod.GET)
    public ResponseMessage setQindex(){
        List quaryList = appQualityService.quary();
        ResponseMessage responseMessage = this.genResponseMessage();
        if(quaryList!=null && quaryList.size()!=0){
            boolean b = appQualityService.setQindex(quaryList);
            if (b){
                responseMessage.setMsg("处理成功！");
            }else {
                responseMessage.setMsg("处理失败！");
            }
        }else {
            return responseMessage.setMsg("没有需要处理数据！！！");
        }
        return responseMessage;
    }
}
