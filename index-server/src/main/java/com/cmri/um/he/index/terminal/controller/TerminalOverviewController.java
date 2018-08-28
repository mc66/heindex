package com.cmri.um.he.index.terminal.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.terminal.service.TerminalOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 终端品牌
     * @return
     */
    @RequestMapping(value = "quary-brand",method = RequestMethod.GET)
    public ResponseMessage quaryBrand(){
        List<Map<String,Object>> list = terminalOverviewService.quaryBrand();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 查询指定月份终端型号排行榜
     * @param month 指定月份
     * @param page     所要查询的分页，从1开始
     * @param step     每页的记录容量
     * @return 结果集
     * */
    @RequestMapping(value = "/quary-brand-Page",method = RequestMethod.GET)
    public ResponseMessage quaryBrandPage(@RequestParam String month, @RequestParam Integer page, @RequestParam Integer step){

        return terminalOverviewService.findBrandPage(month,page,step).updateResponse(genResponseMessage());
    }

    /**
     * 查询指定月份终端型号排行榜
     * @param month 指定月份
     * @param page     所要查询的分页，从1开始
     * @param step     每页的记录容量
     * @return 结果集
     * */
    @RequestMapping(value = "/quary-brand-type",method = RequestMethod.GET)
    public ResponseMessage quaryBrandType(@RequestParam String month, @RequestParam Integer page, @RequestParam Integer step,Integer pid,Integer bid){
        if (pid == null && bid == null) {
            return terminalOverviewService.findBrandPage(month,page,step).updateResponse(genResponseMessage());
        } else {
            return terminalOverviewService.findBrand(month,page,step,pid,bid).updateResponse(genResponseMessage());
        }

    }
}
