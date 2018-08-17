package com.cmri.um.he.index.monument.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.monument.service.AppMonumentProductService;
import com.cmri.um.he.index.receivable.CommentParticularsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间通用方法
 * @author machao
 * Created on 2018/8/10
 */
@RestController
@CrossOrigin
public class AppMonumentProductController extends ZRestController {

    @Autowired
    AppMonumentProductService appMonumentProductService;

    /**
     * 查询每日评论数量统计
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-comment-statistics",method = RequestMethod.GET)
    public ResponseMessage quaryDayCommentStatistics(Integer app,String startTime,String endTime){
        List<Map<String,Object>> list = null;
        try {
            list = appMonumentProductService.quaryDayCommentStatistics(app,startTime,endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 查询评论详情
     * @param commentParticularsVO
     * @return
     */
    @RequestMapping(value = "quary-comment-particulars",method = RequestMethod.GET)
    public ResponseMessage quaryCommentParticulars(CommentParticularsVO commentParticularsVO){
        ResponseMessage responseMessage = appMonumentProductService.quaryCommentParticulars(commentParticularsVO).updateResponse(genResponseMessage());
        return responseMessage;
    }

    /**
     * 查询热词
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-hot-word",method = RequestMethod.GET)
    public ResponseMessage quaryHotWord(Integer app, String startTime, String endTime){
        List<String> list = appMonumentProductService.quaryHotWord(app,startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }

    /**
     * 查询评论来源
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-comment-source",method = RequestMethod.GET)
    public ResponseMessage quaryCommentSource(Integer app, String startTime, String endTime){
        List<String> list = appMonumentProductService.quaryCommentSource(app,startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }
}
