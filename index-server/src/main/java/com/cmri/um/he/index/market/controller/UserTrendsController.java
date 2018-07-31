package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.AppMarketGeneralService;
import com.cmri.um.he.index.market.service.UserTrendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
@RestController
@CrossOrigin
public class UserTrendsController extends ZRestController{

    @Autowired
    private UserTrendsService userTrendsService;
    @Autowired
    private AppMarketGeneralService appMarketGeneralService;

    /**
     *  查询本月数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    @RequestMapping(value = "/queryMonthsData",method = RequestMethod.GET)
    public ResponseMessage queryMonthsData(@RequestParam int category, @RequestParam int app, @RequestParam String month){
        List<Map<String, Object>> queryMonthsData = userTrendsService.queryMonthsDataList(category,app,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryMonthsData!=null&&queryMonthsData.size()>0){
            responseMessage.set("MonthsData",queryMonthsData);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }

    /**
     * 查询累计用户数 month2
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @RequestMapping(value = "/queryTrendsCumulative",method = RequestMethod.GET)
    public ResponseMessage queryTrendsCumulative(@RequestParam int app, @RequestParam String month1,@RequestParam String month2,@RequestParam String status) {
        List<Map<String, Object>> list=appMarketGeneralService.getCumulativeList(app, month1, month2,status);;
        ResponseMessage responseMessage = this.genResponseMessage();
        if (list!=null&&list.size()>0){
            responseMessage.set("trendsCumulative",list);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }

    /**
     *   查询用户参透率
     * @param app
     * @param month1
     * @param month2
     * @param status
     * @return
     */
    @RequestMapping(value = "/queryPenetration",method = RequestMethod.GET)
    public ResponseMessage queryPenetration(@RequestParam int app, @RequestParam String month1,@RequestParam String month2,@RequestParam String status) {
        List<Map<String, Object>> queryPenetration = userTrendsService.queryPenetrationList(app, month1, month2, status);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryPenetration != null && queryPenetration.size() > 0) {
            responseMessage.set("penetration", queryPenetration);
        } else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }

    /**
     *  查询用户参透率统计表
     * @param app
     * @param month1
     * @param month2
     * @param status
     * @return
     */
    @RequestMapping(value = "/queryUserStatistics",method = RequestMethod.GET)
    public ResponseMessage queryUserStatistics(@RequestParam int app, @RequestParam String month1,@RequestParam String month2,@RequestParam String status) {
        List<Map<String, Object>> queryUserStatistics=userTrendsService.queryUserStatisticsList(app, month1, month2,status);;
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryUserStatistics!=null&&queryUserStatistics.size()>0){
            responseMessage.set("userStatistics",queryUserStatistics);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }







}
