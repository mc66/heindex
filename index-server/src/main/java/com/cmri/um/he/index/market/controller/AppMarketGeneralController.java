package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.AppMarketGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 应用概括
 * @author shihao
 * Created on 2018/7/30
 */
@RestController
@CrossOrigin
public class AppMarketGeneralController extends ZRestController {

    @Autowired
    private AppMarketGeneralService appMarketGeneralService;

    /**
     * 查询人均时长和人均流量
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "query-length-time",method = RequestMethod.GET)
    public ResponseMessage getLengthTime(@RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime,@RequestParam String status){

        List<Map<String , Object>> list = appMarketGeneralService.getLengthTime(app,startTime,endTime,status);
        List<String> list1=new ArrayList<>();
        List<Object> list2=new ArrayList<>();
        List<Object> list3=new ArrayList<>();
        for (Map<String, Object> stringObjectMap : list) {
            String month = (String)stringObjectMap.get("month");
            list1.add(month);
            Object lengthTime =  stringObjectMap.get("lengthTime");
            list2.add(lengthTime);
            Object flow =  stringObjectMap.get("flow");
            list3.add(flow);
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("month",list1);
        responseMessage.set("lengthTime",list2);
        responseMessage.set("flow",list3);
        return responseMessage;
    }

    /**
     * 查询应用概括统计
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-general-statistic",method = RequestMethod.GET)
    public ResponseMessage quaryGeneralStatistic(@RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime,@RequestParam String status){
        List<Map<String , Object>> list = appMarketGeneralService.quaryGeneralStatistic(app,startTime,endTime,status);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }


    /**
     * 查询次月存留率
     * @return
     */
    @RequestMapping(value = "app-market-rate",method = RequestMethod.GET)
    public ResponseMessage getRate(@RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime,@RequestParam String status){
        List list = appMarketGeneralService.getRate(app,startTime,endTime,status);
        ResponseMessage responseMessage=this.genResponseMessage();
        responseMessage.set("list",list);

        return responseMessage;
    }

    /**
     * 查询月活跃用户
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "app-market-num",method = RequestMethod.GET)
    public ResponseMessage getUserNumber(Integer app,String startTime,String endTime,@RequestParam String status){
        List<Map<String, Object>> list=appMarketGeneralService.getUserNumber(app,startTime,endTime,status);
        List<Object> list1=new ArrayList();
        List<Object> list2=new ArrayList();
        List<String> list3=new ArrayList();
        for (Map<String, Object> stringObjectMap : list) {
            Object new_user =stringObjectMap.get("new_user");
            Object mau_number = stringObjectMap.get("mau_number");
            String month =(String) stringObjectMap.get("month");
            list1.add(new_user);
            list2.add(mau_number);
            list3.add(month);
        }
        ResponseMessage responseMessage=this.genResponseMessage();
        responseMessage.set("list",list);
        responseMessage.set("new_user",list1);
        responseMessage.set("mau_number",list2);
        responseMessage.set("month",list3);
        return responseMessage;
    }

    /**
     * 查询当月数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    @RequestMapping(value = "queryMonthData",method = RequestMethod.GET)
    public ResponseMessage queryMonthData(@RequestParam int category,@RequestParam int app, @RequestParam String month){
        List<Map<String, Object>> queryMonthDataList = appMarketGeneralService.getAppMarketList(category,app,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryMonthDataList!=null&&queryMonthDataList.size()>0){
            responseMessage.set("marketList",queryMonthDataList);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }

    /**
     * 查询累计用户数 month2
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/queryCumulative",method = RequestMethod.GET)
    public ResponseMessage queryCumulative(@RequestParam int app, @RequestParam String startTime,@RequestParam String endTime,@RequestParam String status) {
        List<Map<String, Object>> list=appMarketGeneralService.getCumulativeList(app, startTime, endTime,status);
        List<Object> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        for ( Map<String, Object> map :list) {
            Object total_user =  map.get("total_user");
           String month = (String) map.get("month");
           list1.add(total_user);
           list2.add(month);
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("total_user",list1);
        responseMessage.set("month",list2);
        return responseMessage;
    }

}
