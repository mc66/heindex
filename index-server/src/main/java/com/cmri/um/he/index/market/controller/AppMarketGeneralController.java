package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.AppMarketGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AppMarketGeneralController extends ZRestController {

    @Autowired
    private AppMarketGeneralService appMarketGeneralService;

    /**
     * 查询人均时长和人均流量
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @RequestMapping(value = "query-length-time",method = RequestMethod.GET)
    public ResponseMessage getLengthTime(@RequestParam Integer app , @RequestParam String month1, @RequestParam String month2){

        List<Map<String , Object>> list = appMarketGeneralService.getLengthTime(app,month1,month2);
        List<String> list1=new ArrayList<>();
        List<Double> list2=new ArrayList<>();
        List<Double> list3=new ArrayList<>();
        for (Map<String, Object> stringObjectMap : list) {
            String month = (String)stringObjectMap.get("month");
            list1.add(month);
            Double lengthTime = (Double) stringObjectMap.get("lengthTime");
            list2.add(lengthTime);
            Double flow = (Double) stringObjectMap.get("flow");
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
     * @param month1
     * @param month2
     * @return
     */
    @RequestMapping(value = "quary-general-statistic",method = RequestMethod.GET)
    public ResponseMessage quaryGeneralStatistic(@RequestParam Integer app , @RequestParam String month1, @RequestParam String month2){
        List<Map<String , Object>> list = appMarketGeneralService.quaryGeneralStatistic(app,month1,month2);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

}
