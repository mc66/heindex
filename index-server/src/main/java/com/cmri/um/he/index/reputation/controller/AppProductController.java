package com.cmri.um.he.index.reputation.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.reputation.service.AppProductService;
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
public class AppProductController extends ZRestController {

    @Autowired
    AppProductService appProductService;

    /**
     * 查询每日评论数量统计
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "quary-comment-statistics",method = RequestMethod.GET)
    public ResponseMessage quaryDayCommentStatistics(Integer app,String startTime,String endTime){

        //long start = System.currentTimeMillis(); // 获取开始时间


        List<Map<String,Object>> list = null;
        try {
            list = appProductService.quaryDayCommentStatistics(app,startTime,endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);


        //long end = System.currentTimeMillis(); // 获取结束时间
        //long time = end-start;
        return responseMessage;
    }

    /**
     * 查询评论详情
     * @param commentParticularsVO
     * @return
     */
    @RequestMapping(value = "quary-comment-particulars",method = RequestMethod.GET)
    public ResponseMessage quaryCommentParticulars(CommentParticularsVO commentParticularsVO){
        ResponseMessage responseMessage = appProductService.quaryCommentParticulars(commentParticularsVO).updateResponse(genResponseMessage());
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
        List<String> list = appProductService.quaryHotWord(app,startTime,endTime);
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
        List<String> list = appProductService.quaryCommentSource(app,startTime,endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;
    }
}
