package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.AppMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 市场指数的关键指标数据
 * @author shihao/machao
 * Created on 2018/6/13
 */
@RestController
@CrossOrigin
public class AppMarketController extends ZRestController {

    @Autowired
    private AppMarketService appMarketService;

    @RequestMapping(value = "/app-market-list",method = RequestMethod.GET)
    public ResponseMessage getAppMarket(@RequestParam int category,@RequestParam String month){
        List<Map<String, Object>> appMarketList = appMarketService.getAppMarketList(category,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (appMarketList!=null&&appMarketList.size()>0){
            responseMessage.set("marketList",appMarketList);
        }else {
            responseMessage.setMsg("现在还没有数据！");
        }
        return responseMessage;
    }

    @RequestMapping(value = "app-market-query",method = RequestMethod.GET)
    public ResponseMessage getMarket(@RequestParam Integer category ,@RequestParam String month ){
        List<Map<String ,Object>> list=appMarketService.getMarket(category,month);
        ResponseMessage responseMessage=this.genResponseMessage();
        if (list!=null&&list.size()>0){
            responseMessage.set("list",list);
        }else {
            responseMessage.setMsg("未添加数据！");
        }
        return responseMessage;

    }

    @RequestMapping(value = "app-market-querylist",method = RequestMethod.GET)
    public ResponseMessage getMarketList(@RequestParam Integer category ,@RequestParam String month1, @RequestParam String month2 ){
        List<Map<String ,Object>> lists=appMarketService.getMarketList(category,month1,month2);
        ResponseMessage responseMessage=this.genResponseMessage();
        if (lists!=null&&lists.size()>0){
            responseMessage.set("lists",lists);
        }else {
            responseMessage.setMsg("未添加数据！");
        }
        return responseMessage;

    }

}
