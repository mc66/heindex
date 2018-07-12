package com.cmri.um.he.index.operations.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.operations.dao.AppConversionDao;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.service.AppConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 计算值转标准值
 *
 * @author yinjunjun
 * Created on 2018/07/11
 */
@RestController
@CrossOrigin
@RequestMapping("/app-calculation")
public class AppConversionController extends ZRestController {

    @Autowired
    private AppConversionService service;
    @Autowired
    private AppConversionDao dao;

    /**
     * 查询出海没有转换为标准值得数据
     * */
    @RequestMapping(value = "/query-operations",method = RequestMethod.GET)
    public ResponseMessage getOperations(@RequestParam Integer category, @RequestParam String month){
        List<Map<String, Object>> maps = service.queryUnConversion(category, month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("data",maps);
        return responseMessage;
    }

    /**
     * 全部转换为标准值
     * */
    @RequestMapping(value = "/save-operations",method = RequestMethod.POST)
    public ResponseMessage saveOperations(@RequestBody Map<String,List<AppCalculationOperationsEntity>> map){
        List<AppCalculationOperationsEntity> data = map.get("data");
        service.saveAll(data);
        ResponseMessage responseMessage = this.genResponseMessage();
        return responseMessage;
    }

    /**
     * 查询出所有的期数
     * */
    @RequestMapping(value = "/query-month",method = RequestMethod.GET)
    public ResponseMessage getMonth(){
        List<Map<String, Object>> months = service.findMonths();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",months);
        return responseMessage;

    }

    @RequestMapping(value = "/ceshi",method = RequestMethod.GET)
    public ResponseMessage ceshi(){
        List<Map<String, Object>> months = dao.ceshi();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",months);
        return responseMessage;

    }
}
