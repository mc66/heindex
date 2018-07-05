package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppSecondQualityDelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 二级页面产品品质时延功耗折线图
 * @author lch
 * Created on 2018/07/03 09:39
 */
@RestController
@RequestMapping("/app-second-quality-delay")
@CrossOrigin
public class AppSecondQualityDelayController extends ZRestController {
    @Autowired
    private AppSecondQualityDelayService delayService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam Integer id,@RequestParam String measuring,@RequestParam String month) {
        List<Map<String, Object>> list3g = delayService.findQualityMeasureBySome(id,measuring, "3g", month);
        List<Map<String, Object>> list4g = delayService.findQualityMeasureBySome(id,measuring, "4g", month);
        List<Map<String, Object>> listWlan = delayService.findQualityMeasureBySome(id,measuring, "WLAN", month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list3g",list3g);
        responseMessage.set("list4g",list4g);
        responseMessage.set("listWlan",listWlan);
        return responseMessage;
    }
}
