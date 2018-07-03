package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 报告期数
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@RestController
@RequestMapping("/app-month")
@CrossOrigin
public class AppMonthController extends ZRestController {
    @Autowired
    private AppMonthService appMonthService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(HttpServletResponse response){
        List<Map<String, Object>> maps = appMonthService.findMonths();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items",maps);
        Map<String, Object> map = maps.get(0);
        String month = (String)map.get("month");
        Cookie cookie=new Cookie("newmonth",month);
        cookie.setMaxAge(300);
        response.addCookie(cookie);
        return responseMessage;
    }
}
