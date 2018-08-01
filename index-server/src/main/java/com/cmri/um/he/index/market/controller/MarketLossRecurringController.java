package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.MarketLossRecurringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * 市场指数的流失与回流页面
 * @author machao
 * Created on 2018/7/27
 */
@RestController
@CrossOrigin
public class MarketLossRecurringController extends ZRestController{

    @Autowired
    MarketLossRecurringService marketLossRecurringService;

    /**
     * 查询月流失回流市场指数
     * @param app
     * @param month
     * @return
     */
    @RequestMapping(value = "quary-loss-recurring-exponent",method = RequestMethod.GET)
    public ResponseMessage quaryLossRecurringExponent(@RequestParam Integer app , @RequestParam String month){
        Map<String, Object> map=marketLossRecurringService.quaryLossRecurringExponent(app,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("map",map);
        return responseMessage;
    }

    /**
     * 查询用户流失情况
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-loss-exponent",method = RequestMethod.GET)
    public ResponseMessage quaryUserLoss(@RequestParam String status, @RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime){
        List<Map<String , Object>> list=marketLossRecurringService.quaryUserLoss(status,app,startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 查询用户回流情况
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-recurring-exponent",method = RequestMethod.GET)
    public ResponseMessage quaryUserRecurring(@RequestParam String status, @RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime){
        List<Map<String , Object>> list=marketLossRecurringService.quaryUserRecurring(status,app,startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 查询用户情况统计表
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-statistical-table",method = RequestMethod.GET)
    public ResponseMessage quaryStatisticalTable(@RequestParam String status, @RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime){
        List<Map<String , Object>> list=marketLossRecurringService.quaryStatisticalTable(status,app,startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

}
