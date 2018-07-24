package com.cmri.um.he.index.operations.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.operations.service.AppDimensionsWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 维度权重管理
 * @author machao
 * Created on 2018/7/17
 */
@RestController
@CrossOrigin
public class AppDimensionsWeightController extends ZRestController{

    @Autowired
    AppDimensionsWeightService appDimensionsWeightService;

    /**
     * 展示所有维度权重
     * @return
     */
    @RequestMapping(value = "/dimensions-list",method = RequestMethod.GET)
    public ResponseMessage getAllDimensions(@RequestParam Integer aspect, @RequestParam Integer page, @RequestParam Integer step) {
        if (aspect == 0) {
            return appDimensionsWeightService.getAllDimensions(page, step).updateResponse(genResponseMessage());
        } else {
            return appDimensionsWeightService.getAllDimensionsByAspect (aspect, page, step).updateResponse(genResponseMessage());
        }
    }


    @RequestMapping(value = "/update-weight",method = RequestMethod.GET)
    public ResponseMessage updateDimensions(@RequestParam Integer id,@RequestParam double weight){
        appDimensionsWeightService.updateWeight(id,weight);
        ResponseMessage responseMessage = this.genResponseMessage();
        return responseMessage;
    }
}
