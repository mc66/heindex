package com.cmri.um.he.index.monument.dao;

import com.cmri.um.he.index.monument.mapper.AppDetailsWordsMapper;
import com.cmri.um.he.index.receivable.CommentParticularsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间
 * @author limin
 * Created on 2018/8/16
 */
@Repository
public class AppDetailsWordsDao extends BaseDao{

    @Autowired
    private AppDetailsWordsMapper mapper;


    /**
     * 查询日各情感倾向评论数
     * @param comment
     * @param date
     * @return
     */
    public Map<String,Object> quaryDayqQuantitative(String comment, String date) {
        return mapper.quaryDayqQuantitative(comment,date);
    }
    /**
     * 半月查询各情感倾向评论数
     * @param comment
     * @param startTime
     * @param endTime
     * @return
     */
    public Map<String,Object> quaryquantitative(String comment, String startTime, String endTime) {
        return mapper.quaryquantitative(comment,startTime,endTime);
    }
    /**
     * 查询详情条数
     * @param commentParticularsVO
     * @return
     */
    public int count(CommentParticularsVO commentParticularsVO) {
        return mapper.count(commentParticularsVO);
    }

    /**
     *   分页查询详情
     * @param commentParticularsVO
     * @return
     */
    public List<Map<String,Object>> quaryCommentParticulars(CommentParticularsVO commentParticularsVO, Integer page, Integer step) {
        commentParticularsVO.setOffset(offset(page,step));
        commentParticularsVO.setRows(step);
        return addNo(mapper.quaryCommentParticulars(commentParticularsVO));
    }

    /**
     *   查询热词
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> quaryHotWords(String startTime, String endTime) {
        return mapper.quaryHotWords(startTime,endTime);
    }

    /**
     *   查询评论来源
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> quarySourceComment(String startTime, String endTime) {
        return mapper.quarySourceComment(startTime,endTime);
    }



    /**
     * 为记录添加序号
     */
    private List<Map<String, Object>> addNo(List<Map<String, Object>> items) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).put("no", i);
        }

        return items;
    }



}
