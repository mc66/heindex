package com.cmri.um.he.index.quality.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 计算值转标准值
 *
 * @author yinjunjun
 * Created on 2018/6/25
 */
@RestController
@CrossOrigin
public class StandardController extends ZRestController {

    @Autowired
    private StandardService standardService;

    /**
     * 查询出没有转换为标准值的数据集合
     * */
    @RequestMapping(value = "/app-quality-calculation-list",method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam Integer category,@RequestParam String month){
        List<AppCalculationQualityEntity> maps = standardService.unprocessedStandard(category,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("data",maps);
        return responseMessage;
    }

    /**
     * 将未转换的数据转换为标准值
     * */
    @RequestMapping(value = "/app-quality-calculation",method = RequestMethod.POST)
    public ResponseMessage saveStandard(@RequestBody  Map<String,List<AppCalculationQualityEntity>> map ){
        List<AppCalculationQualityEntity> appCalculationQuality = map.get("data");
        standardService.standard(appCalculationQuality);
        ResponseMessage responseMessage = this.genResponseMessage();
        return responseMessage;
    }
}
