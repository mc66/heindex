package com.cmri.um.he.index.quality.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.quality.entity.AppOriginalDelayEntity;
import com.cmri.um.he.index.quality.service.AppOriginalDelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 品质得分中时延、功耗
 * @author lch
 * Created on 2018/06/27 09:53
 */
@RestController
@CrossOrigin
public class AppOriginalDelayController extends ZRestController{

    @Autowired
    private AppOriginalDelayService service;

    /**
     * 根据应用类别查app
     * @param category
     * @return
     */
    @RequestMapping(value = "/app-findAppByCategory",method = RequestMethod.GET)
    public ResponseMessage findAppByCategory(@RequestParam Integer category) {
        List<Map<String, Object>> resultList = service.findAppByCategory(category);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("apps",resultList);
        return responseMessage;
    }

    @RequestMapping(value = "/app-quality-original-delay",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage saveAppOriginalDelayList(@RequestBody Map<String,List<AppOriginalDelayEntity>> map) throws IOException {
        List<AppOriginalDelayEntity> list = map.get("data");
        boolean b1 = service.saveAppOriginalDelayList(list);
        boolean b2 = service.dealAppOriginalDelayList(list);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (b1 && b2){
            responseMessage.setMsg("提交成功!");
        }else {
            responseMessage.setMsg("提交失败!");
        }
        return responseMessage;
    }

}
