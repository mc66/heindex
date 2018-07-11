package com.cmri.um.he.index.operations.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;
import com.cmri.um.he.index.operations.service.AppCmtariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 	渠道、营销、资费、服务原始数据
 *
 * @author limin
 * Created on 2018/7/10
 */
public class AppCmtariffController extends ZRestController{

    @Autowired
    private AppCmtariffService appCmtariffService;

    @RequestMapping(value = "/saveAppOriginalContentEntity",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage saveAppOriginalContentEntity(@RequestBody Map<String,List<AppOriginalContentEntity>> map) throws IOException {
        List<AppOriginalContentEntity> list = map.get("data");
        boolean b1 = appCmtariffService.saveAppOriginalContentEntity(list);
        boolean b2 = appCmtariffService.dealAppOriginalContentEntity(list);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (b1 && b2){
            responseMessage.setMsg("提交成功!");
        }else {
            responseMessage.setMsg("提交失败!");
        }
        return responseMessage;
    }

}
