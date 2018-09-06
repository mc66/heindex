package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.AppMarketAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 用户新增
 * @author shihao
 * Created on 2018/8/1
 */
@RestController
@CrossOrigin
public class AppMarketAddController extends ZRestController {

    @Autowired
    private AppMarketAddService appMarketAddService;

    /**
     * 应用新增页面本月数据展示
     * @param category
     * @param app
     * @param month
     * @return
     */
    @RequestMapping(value = "app-market-addnumber",method = RequestMethod.GET)
    public ResponseMessage getAppNumber(@RequestParam Integer category , @RequestParam Integer app, @RequestParam String month){
        List<Map<String, Object>> list=appMarketAddService.getAppNumber(category,app,month);
        ResponseMessage responseMessage =this.genResponseMessage();
        responseMessage.set("list",list);

        return  responseMessage;
    }

    /**
     * 应用新增页面新增活跃用户展示
     * @param app
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    @RequestMapping(value = "app-market-adduser",method = RequestMethod.GET)
    public ResponseMessage getAppUser(@RequestParam Integer app, @RequestParam String startTime,@RequestParam String endTime,String status){
        List<Map<String, Object>> list=appMarketAddService.getAppUser(app,startTime,endTime,status);
        ResponseMessage responseMessage =this.genResponseMessage();
        responseMessage.set("list",list);
        return  responseMessage;
    }

    /**
     * 应用新增页面新增次月留存率展示
     * @param app
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    @RequestMapping(value = "app-market-addrate",method = RequestMethod.GET)
    public ResponseMessage getRate(@RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime,@RequestParam String status){
        List list = appMarketAddService.getAddRate(app,startTime,endTime,status);
        ResponseMessage responseMessage=this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 应用新增页面用户新增数据统计表展示
     * @param app
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    @RequestMapping(value = "app-market-addcount",method = RequestMethod.GET)
    public ResponseMessage getCountNumber(@RequestParam Integer app , @RequestParam String startTime, @RequestParam String endTime,@RequestParam String status){
        List list = appMarketAddService.getCountNumber(app,startTime,endTime,status);
        ResponseMessage responseMessage=this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }
}
