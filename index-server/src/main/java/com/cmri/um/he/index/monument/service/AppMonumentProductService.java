package com.cmri.um.he.index.monument.service;


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

}
