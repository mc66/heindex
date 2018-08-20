package com.cmri.um.he.index.appview.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.appview.service.AppSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 某类应用品质、运营的总结
 *
 * @author yinjunjun
 * Created on 2018/6/19
 */
@RestController
@RequestMapping("/app-summary")
public class AppSummaryController extends ZRestController{

    @Autowired
    private AppSummaryService appSummaryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam String month,@RequestParam Integer category,@RequestParam Integer flag){
        ResponseMessage responseMessage = this.genResponseMessage();
        if (flag == 1){
            List<Map<String, Object>> quality = appSummaryService.findQuality(month, category);
            responseMessage.set("summary",quality);
        } else{
            List<Map<String, Object>> operation = appSummaryService.findOperation(month, category);
            responseMessage.set("summary",operation);
        }
        return responseMessage;
    }

}
