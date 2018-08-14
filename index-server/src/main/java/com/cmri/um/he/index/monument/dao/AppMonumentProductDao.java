package com.cmri.um.he.index.monument.dao;


import com.cmri.um.he.index.monument.mapper.AppMonumentProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间通用方法
 * @author machao
 * Created on 2018/8/10
 */
@Repository
public class AppMonumentProductDao extends BaseDao {

    @Autowired
    AppMonumentProductMapper appMonumentProductMapper;

    /**
     * 查询日评论数量统计
     * @param app
     * @param date
     * @return
     */
    public Map<String,Integer> quaryDayCommentAmount(Integer app, String date){
        return appMonumentProductMapper.quaryDayCommentAmount(app,date);
    }
    /**
     * 查询日评论数量统计
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public Map<String,Object> quaryCommentAmount(Integer app, String startTime, String endTime){
        return appMonumentProductMapper.quaryCommentAmount(app,startTime,endTime);
    }
}
