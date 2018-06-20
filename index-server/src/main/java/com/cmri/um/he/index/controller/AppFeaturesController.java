package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 查询应用的关键点数据
 *
 * @author limin
 * Created on 2018/6/13
 */
@RestController
@RequestMapping("/app-features")
@CrossOrigin
public class AppFeaturesController extends ZRestController {

    @Autowired
    private AppFeaturesService appFeaturesService;




//    @RequestMapping("selectAppFeatures")
//    public Object selectAppFeatures(@RequestParam String month,@RequestParam Integer app,@RequestParam Integer page,@RequestParam Integer rows){
//        Map<String,Object> str=appFeaturesService.selectAppFeaturesList(month,app,page,rows);
//        return str;
//    }

    /**
     *   查询应用的关键点数据
     * @param month
     * @param category
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam String month, @RequestParam Integer category) {
        List<Map<String, Object>> maps = appFeaturesService.find(month, category);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("emotions",maps);
        return responseMessage;
    }


}
