package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.AppMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        responseMessage.set("list",list);
        return responseMessage;

    }
}
