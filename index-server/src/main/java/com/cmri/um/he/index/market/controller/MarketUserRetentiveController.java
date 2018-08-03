package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.MarketUserRetentiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 市场指数的用户留存页面
 * @author machao
 * Created on 2018/7/31
 */
@RestController
@CrossOrigin
public class MarketUserRetentiveController extends ZRestController{

    @Autowired
    MarketUserRetentiveService marketUserRetentiveService;

    /**
     * 查询月用户留存市场指数
     * @param app
     * @param month
     * @return
     */
    @RequestMapping(value = "quary-user-retentive-exponent",method = RequestMethod.GET)
    public ResponseMessage quaryUserRetentiveExponent(@RequestParam Integer app , @RequestParam String month){
        List<Map<String , Object>> list=marketUserRetentiveService.quaryUserRetentiveExponent(app,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 查询用户留存数
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-user-retentive-num",method = RequestMethod.GET)
    public ResponseMessage quaryUserRetentiveNum(@RequestParam String status, @RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime){
        List<Map<String , Object>> list=marketUserRetentiveService.quaryUserRetentiveNum(status,app,startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 查询用户留存率
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-user-retentive-rate",method = RequestMethod.GET)
    public ResponseMessage quaryUserRetentiveRate(@RequestParam String status, @RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime){
        List<Map<String , Object>> list=marketUserRetentiveService.quaryUserRetentiveRate(status,app,startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 查询用户留存统计表
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-User-retentive-table",method = RequestMethod.GET)
    public ResponseMessage quaryUserRetentiveTable(@RequestParam String status, @RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime){
        List<Map<String , Object>> list=marketUserRetentiveService.quaryUserRetentiveTable(status,app,startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

}
