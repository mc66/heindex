package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.UserActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author  limin
 */
@RestController
@CrossOrigin
public class UserActiveController extends ZRestController {

    @Autowired
    private UserActiveService userActiveService;

    /**
     *   查询用户活跃数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    @RequestMapping(value = "/queryActiveMonthData",method = RequestMethod.GET)
    public ResponseMessage queryActiveMonthData(@RequestParam int category, @RequestParam int app, @RequestParam String month){
        List<Map<String, Object>> queryActiveMonthDataList = userActiveService.getActiveMonthData(category,app,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryActiveMonthDataList!=null&&queryActiveMonthDataList.size()>0){
            responseMessage.set("ActiveMonthData",queryActiveMonthDataList);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }

    /**
     *  查询用户活跃情况
     * @param app
     * @param month1
     * @param month2
     * @param status
     * @return
     */
    @RequestMapping(value = "/queryActivity",method = RequestMethod.GET)
    public ResponseMessage queryActivity(@RequestParam int app, @RequestParam String month1, @RequestParam String month2,@RequestParam String status){
        List<Map<String, Object>> queryActivity = userActiveService.queryActivityList(month1,app,month2,status);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryActivity!=null&&queryActivity.size()>0){
            responseMessage.set("activity",queryActivity);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }

    /**
     *   查询用户使用天数
     * @param app
     * @param month1
     * @param month2
     * @param status
     * @return
     */
    @RequestMapping(value = "/queryNumberDay",method = RequestMethod.GET)
    public ResponseMessage queryNumberDay(@RequestParam int app, @RequestParam String month1, @RequestParam String month2,@RequestParam String status){
        List<Map<String, Object>> queryNumberDay = userActiveService.queryNumberDayList(month1,app,month2,status);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryNumberDay!=null&&queryNumberDay.size()>0){
            responseMessage.set("numberday",queryNumberDay);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }

    /**
     *  查询使用行为
     * @param app
     * @param month1
     * @param month2
     * @param status
     * @return
     */
    @RequestMapping(value = "/queryBehavior",method = RequestMethod.GET)
    public ResponseMessage queryBehavior(@RequestParam int app, @RequestParam String month1, @RequestParam String month2,@RequestParam String status){
        List<Map<String, Object>> queryBehavior = userActiveService.queryBehaviorList(month1,app,month2,status);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryBehavior!=null&&queryBehavior.size()>0){
            responseMessage.set("behavior",queryBehavior);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }

    /**
     *  查询用户活跃统计表
     * @param app
     * @param month1
     * @param month2
     * @param status
     * @return
     */
    @RequestMapping(value = "/queryStatistical",method = RequestMethod.GET)
    public ResponseMessage queryStatistical(@RequestParam int app, @RequestParam String month1, @RequestParam String month2,@RequestParam String status){
        List<Map<String, Object>> queryStatistical = userActiveService.queryStatisticalList(month1,app,month2,status);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryStatistical!=null&&queryStatistical.size()>0){
            responseMessage.set("behavior",queryStatistical);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }






}
