package com.cmri.um.he.index.monument.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.monument.service.AppDetailsWordsService;
import com.cmri.um.he.index.receivable.CommentParticularsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间
 * @author limin
 * Created on 2018/8/16
 */
@RestController
@CrossOrigin
public class AppDetailsWordsController extends ZRestController {

    @Autowired
    private AppDetailsWordsService appDetailsWordsService;

    /**
     * 查询每日评论数量统计
     * @param word
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quaryquantitative",method = RequestMethod.GET)
    public ResponseMessage quaryquantitative(@RequestParam String word, @RequestParam String startTime,@RequestParam String endTime){
        List<Map<String,Object>> list = null;
        try {
            list = appDetailsWordsService.quaryquantitative(word,startTime,endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 查询月情感指数表
     * @param commentParticularsVO
     * @return
     */
    @RequestMapping(value = "quaryMonthlySentiment",method = RequestMethod.GET)
    public ResponseMessage quaryMonthlySentiment(CommentParticularsVO commentParticularsVO){
        ResponseMessage responseMessage = appDetailsWordsService.quaryMonthlySentiment(commentParticularsVO).updateResponse(genResponseMessage());
        return responseMessage;
    }




}
