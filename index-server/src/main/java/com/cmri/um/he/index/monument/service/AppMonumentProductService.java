package com.cmri.um.he.index.monument.service;


import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.receivable.CommentParticularsVO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间通用方法
 * @author machao
 * Created on 2018/8/10
 */
public interface AppMonumentProductService {

    /**
     * 查询每日评论数量统计
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String , Object>> quaryDayCommentStatistics(Integer app,String startTime,String endTime) throws ParseException;

    /**
     * 查询评论详情
     * @param commentParticularsVO
     * @return
     */
    public PagingData<Map<String, Object>> quaryCommentParticulars(CommentParticularsVO commentParticularsVO);

    /**
     * 查询热词
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> quaryHotWord(Integer app, String startTime, String endTime);

    /**
     * 查询评论来源
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> quaryCommentSource(Integer app, String startTime, String endTime);

}
