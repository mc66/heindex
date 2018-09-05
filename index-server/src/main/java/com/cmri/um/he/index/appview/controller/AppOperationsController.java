package com.cmri.um.he.index.appview.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.appview.service.AppOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 查询单个应用运营数据
 *
 * @author
 * Created on 2018/7/4
 */
@RestController
@RequestMapping("/app-operations")
@CrossOrigin
public class AppOperationsController extends ZRestController {

    @Autowired
    private AppOperationsService appOperationsService;
    /**
     * 查询单个app及月份的数据
     * @param id
     * @param month
     * @return
     */
    @RequestMapping(value = "/monthdata",method = RequestMethod.GET)
    public ResponseMessage getQuliatyData(@RequestParam Integer id, @RequestParam String month){
        List resultList= appOperationsService.getOperationsData(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("quality",resultList);
        return responseMessage;
    }

    /**
     *    查询内容指标
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityContent", method = RequestMethod.GET)
    public ResponseMessage queryQualityContent(@RequestParam Integer id, @RequestParam String month){
        List resultList= appOperationsService.queryQualityContent(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("content",resultList);
        return responseMessage;
    }

    /**
     *    查询渠道指标
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityChannel", method = RequestMethod.GET)
    public ResponseMessage queryQualityChannel(@RequestParam Integer id,@RequestParam String month){
        List resultList= appOperationsService.queryQualityChannel(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("channel",resultList);
        return responseMessage;
    }

    /**
     *    查询营销指标
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityMarket", method = RequestMethod.GET)
    public ResponseMessage queryQualityMarket(@RequestParam Integer id,@RequestParam String month){
        List resultList= appOperationsService.queryQualityMarket(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("market",resultList);
        return responseMessage;
    }

    /**
     *    查询资费指标
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityTariff", method = RequestMethod.GET)
    public ResponseMessage queryQualityTariff(@RequestParam Integer id,@RequestParam String month){
        List resultList= appOperationsService.queryQualityTariff(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("tariff",resultList);
        return responseMessage;
    }

    /**
     *    查询服务体验
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityServe", method = RequestMethod.GET)
    public ResponseMessage queryQualityServe(@RequestParam Integer id,@RequestParam String month){
        List resultList= appOperationsService.queryQualityServe(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("serve",resultList);
        return responseMessage;
    }


    /**
     *    查询运营体验
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityExperience", method = RequestMethod.GET)
    public ResponseMessage queryQualityExperience(@RequestParam Integer id,@RequestParam String month){
        List resultList= appOperationsService.queryQualityExperience(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("experience",resultList);
        return responseMessage;
    }

    /**
     * 查询内容更新和覆盖的峰值
     * @param app
     * @param month
     * @return
     */
    @RequestMapping( value = "get-content", method = RequestMethod.GET)
    public ResponseMessage getContent(@RequestParam Integer app,@RequestParam String month){
        List<Map<String,Object>> list=appOperationsService.getContent(app,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;

    }

}
