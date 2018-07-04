package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查询单个应用品质数据
 * @author shihao
 * Created on 2018/7/2
 */
@RestController
@RequestMapping("/app-quality")
@CrossOrigin
public class AppQualityController extends ZRestController {

    @Autowired
    private AppQualityService appQualityService;

    /**
     * 查询单个app及月份的数据
     * @param id
     * @param month
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage getQuliatyData(@RequestParam Integer id,@RequestParam String month){
        List resultList= appQualityService.getQualityData(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("quality",resultList);
        return responseMessage;
    }

    /**
     *    查询功能指标
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityFeatures", method = RequestMethod.GET)
    public ResponseMessage queryQualityFeatures(@RequestParam Integer id,@RequestParam String month){
        List resultList= appQualityService.queryQualityFeatures(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("features",resultList);
        return responseMessage;
    }

    /**
     *    查询界面指标
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityViews", method = RequestMethod.GET)
    public ResponseMessage queryQualityViews(@RequestParam Integer id,@RequestParam String month){
        List resultList= appQualityService.queryQualityViews(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("views",resultList);
        return responseMessage;
    }

    /**
     *    查询时延指标
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityDelay", method = RequestMethod.GET)
    public ResponseMessage queryQualityDelay(@RequestParam Integer id,@RequestParam String month){
        List resultList= appQualityService.queryQualityDelay(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("delay",resultList);
        return responseMessage;
    }

    /**
     *    查询功耗指标
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityConsume", method = RequestMethod.GET)
    public ResponseMessage queryQualityConsume(@RequestParam Integer id,@RequestParam String month){
        List resultList= appQualityService.queryQualityConsume(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("consume",resultList);
        return responseMessage;
    }

    /**
     *    查询使用体验
     * @param id
     * @param month
     * @return
     *  @author limin
     */
    @RequestMapping( value = "queryQualityExperience", method = RequestMethod.GET)
    public ResponseMessage queryQualityExperience(@RequestParam Integer id,@RequestParam String month){
        List resultList= appQualityService.queryQualityExperience(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("experience",resultList);
        return responseMessage;
    }

}
