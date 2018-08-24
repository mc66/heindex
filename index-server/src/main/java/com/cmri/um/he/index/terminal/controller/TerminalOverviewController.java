package com.cmri.um.he.index.terminal.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.receivable.CommentParticularsVO;
import com.cmri.um.he.index.reputation.service.AppProductService;
import com.cmri.um.he.index.terminal.service.TerminalOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 终端指数——概览分析
 * @author machao
 * Created on 2018/8/23
 */
@RestController
@CrossOrigin
public class TerminalOverviewController extends ZRestController {

    @Autowired
    TerminalOverviewService terminalOverviewService;

    /**
     * 查询省份
     * @return
     */
    @RequestMapping(value = "quary-province",method = RequestMethod.GET)
    public ResponseMessage quaryProvince(){
        List<Map<String,Object>> list = terminalOverviewService.quaryProvince();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 终端指数Top10
     * @param id
     * @param month
     * @return
     */
    @RequestMapping(value = "quary-terminal-exponent",method = RequestMethod.GET)
    public ResponseMessage quaryTerminalExponent(Integer id,String month){
        List<Map<String,Object>> list = terminalOverviewService.quaryTerminalExponent(id,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }
}
