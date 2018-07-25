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

    @RequestMapping(value = "/queryMonthData",method = RequestMethod.GET)
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

    @RequestMapping(value = "/queryCumulative",method = RequestMethod.GET)
    public ResponseMessage queryCumulative(@RequestParam int app, @RequestParam String month1,@RequestParam String month2) {
        List<Map<String, Object>> list;
        if (month1.equals("null")) {
            list = appMarketGeneralService.getCumulative(app, month2);
        } else {
            list = appMarketGeneralService.getCumulativeList(app, month1, month2);
        }
        List<Integer> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        for ( Map<String, Object> map :list) {
           Integer total_user = (Integer) map.get("total_user");
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
