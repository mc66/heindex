package com.cmri.um.he.index.reputation.service;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.receivable.CommentParticularsVO;

import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间
 * @author limin
 * Created on 2018/8/16
 */
public interface AppDetailsWordsService {
    /**
     *  查询每日评论数量统计
     * @param comment
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String,Object>> quaryquantitativeLi(String comment, String startTime, String endTime);

    /**
     *   查询月情感指数表
     * @param commentParticularsVO
     * @return
     */
    PagingData<Map<String, Object>> quaryMonthlySentiment(CommentParticularsVO commentParticularsVO);

    /**
     *  查询热词
     * @param startTime
     * @param endTime
     * @return
     */
    List<String> quaryHotWords(String startTime, String endTime);
    /**
     *  查询评论来源
     * @param startTime
     * @param endTime
     * @return
     */
    List<String> quarySourceComment(String startTime, String endTime);
}
