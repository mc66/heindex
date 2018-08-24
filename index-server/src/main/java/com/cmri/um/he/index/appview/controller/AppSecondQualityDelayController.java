package com.cmri.um.he.index.appview.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.appview.service.AppSecondQualityDelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二级页面产品品质时延功耗折线图
 * @author lch
 * Created on 2018/07/03 09:39
 */
@RestController
@CrossOrigin
public class AppSecondQualityDelayController extends ZRestController {
    @Autowired
    private AppSecondQualityDelayService delayService;

    @RequestMapping(value = "/app-second-quality-delay",method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam Integer id,@RequestParam String measuring,@RequestParam String month) {
        List<Map<String, Object>> list3g = delayService.findQualityMeasureBySome(id,measuring, "3g", month);
        List<Map<String, Object>> list4g = delayService.findQualityMeasureBySome(id,measuring, "4g", month);
        List<Map<String, Object>> listWlan = delayService.findQualityMeasureBySome(id,measuring, "WLAN", month);
        ResponseMessage responseMessage = this.genResponseMessage();
        List list =new ArrayList();
        list.add(list3g);
        list.add(list4g);
        list.add(listWlan);
        responseMessage.set("list",list);
        return responseMessage;
    }

    @RequestMapping(value = "/get-measuring",method = RequestMethod.GET)
    public ResponseMessage getMeasuring(@RequestParam int id){
        List<String> mapList = delayService.findeasureById(id);
        List<String> delayMap = new ArrayList<>();
        List<String> consumMap = new ArrayList<>();
        delayMap.add(mapList.get(0));
        delayMap.add(mapList.get(1));
        delayMap.add(mapList.get(2));
        delayMap.add(mapList.get(3));
        consumMap.add(mapList.get(4));
        consumMap.add(mapList.get(5));
        consumMap.add(mapList.get(6));

        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("delay",delayMap);
        responseMessage.set("consum",consumMap);
        return responseMessage;
    }
}
