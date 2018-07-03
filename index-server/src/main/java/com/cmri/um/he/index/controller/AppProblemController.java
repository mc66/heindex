package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 提供App维度问题列表
 * Created by machao on 2018/7/3.
 */
@RestController
@RequestMapping("/app-problem")
@CrossOrigin
public class AppProblemController extends ZRestController{

    @Autowired
    AppProblemService appProblemService;

    /**
     * 查询app某一个维度问题点方法
     * @param app
     * @param dimensions
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage getAppProblem(int app,String dimensions,String month){
        List<Map<String, Object>> appProblemList = appProblemService.getAppProblem(app, dimensions,month);
        ResponseMessage responseMessage=this.genResponseMessage();
        if (appProblemList!=null && appProblemList.size()!=0){
            responseMessage.set("appProblemList",appProblemList);
        }else {
            responseMessage.set("message","当前维度还没有记录问题！！！");
        }
        return responseMessage;
    }
}
