package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 市场指数，当前是应用的月活
 *
 * @author zhuyin
 * Created on 2018/6/4
 */
@RestController
@RequestMapping("/app-mau")
public class AppMauController extends ZRestController {
    @Autowired
    private AppActiveService activeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam String month, String category, @RequestParam Integer page, @RequestParam Integer step) {
        if (category == null) {
            return activeService.find(month, page, step).updateResponse(genResponseMessage());
        } else {
            return activeService.find(month, category, page, step).updateResponse(genResponseMessage());
        }
    }
}
