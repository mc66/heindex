package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 查询app某维度的问题
 * @author machao
 * Created on 2018/7/4
 */
@RestController
@CrossOrigin
public class AppProblemController extends ZRestController{

    @Autowired
    AppProblemService appProblemService;

    /**
     * 查询app某一个维度产品品质问题的方法
     * @param id
     * @param dimensions
     * @param month
     * @return
     */
    @RequestMapping(value = "/app-quality-problem",method = RequestMethod.GET)
    public ResponseMessage getAppQualityProblem(HttpServletRequest request,int id, String dimensions, String month)throws IOException{
        String dimensions1 = request.getParameter("dimensions");
        dimensions = new String(dimensions1.getBytes("iso-8859-1"), "utf-8");
        List<Map<String, Object>> appQualityProblemList = appProblemService.getAppQualityProblem(id, dimensions,month);
        ResponseMessage responseMessage=this.genResponseMessage();
        if (appQualityProblemList!=null && appQualityProblemList.size()!=0){
            responseMessage.set("appQualityProblemList",appQualityProblemList);
        }else {
            responseMessage.set("message","当前维度还没有记录问题！！！");
        }
        return responseMessage;
    }


    /**
     * 查询app某一个维度产品运营问题的方法
     * @param id
     * @param dimensions
     * @param month
     * @return
     */
    @RequestMapping(value = "/app-operation-problem",method = RequestMethod.GET)
    public ResponseMessage getAppOperationProblem(HttpServletRequest request,int id,String dimensions,String month)throws IOException{
        String dimensions1 = request.getParameter("dimensions");
        dimensions = new String(dimensions1.getBytes("iso-8859-1"), "utf-8");
        List<Map<String, Object>> appOperationProblemList = appProblemService.getAppOperationProblem(id, dimensions,month);
        ResponseMessage responseMessage=this.genResponseMessage();
        if (appOperationProblemList!=null && appOperationProblemList.size()!=0){
            responseMessage.set("appOperationProblemList",appOperationProblemList);
        }else {
            responseMessage.set("message","当前维度还没有记录问题！！！");
        }
        return responseMessage;
    }
}
