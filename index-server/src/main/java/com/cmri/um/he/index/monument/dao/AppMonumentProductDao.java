package com.cmri.um.he.index.monument.dao;


import com.cmri.um.he.index.monument.mapper.AppMonumentProductMapper;
import com.cmri.um.he.index.receivable.CommentParticularsVO;
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
     * 查询日各情感倾向评论数
     * @param app
     * @param date
     * @return
     */
    public Map<String,Object> quaryDayCommentAmount(Integer app, String date){
        return appMonumentProductMapper.quaryDayCommentAmount(app,date);
    }
    /**
     * 半月查询各情感倾向评论数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public Map<String,Object> quaryCommentAmount(Integer app, String startTime, String endTime){
        return appMonumentProductMapper.quaryCommentAmount(app,startTime,endTime);
    }

    /**
     * 分页查询评论详情
     * @param commentParticularsVO
     * @return
     */
    public List<Map<String, Object>> quaryCommentParticulars(CommentParticularsVO commentParticularsVO){
        return appMonumentProductMapper.quaryCommentParticulars(commentParticularsVO);
    }

    /**
     * 查询评论条数
     * @param commentParticularsVO
     * @return
     */
    public int count(CommentParticularsVO commentParticularsVO) {
        return appMonumentProductMapper.count(commentParticularsVO);
    }

    /**
     * 查询热词
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> quaryHotWord(Integer app, String startTime, String endTime) {
        return appMonumentProductMapper.quaryHotWord(app,startTime,endTime);
    }

    /**
     * 查询评论来源
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> quaryCommentSource(Integer app, String startTime, String endTime) {
        return appMonumentProductMapper.quaryCommentSource(app,startTime,endTime);
    }
}
