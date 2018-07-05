package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
