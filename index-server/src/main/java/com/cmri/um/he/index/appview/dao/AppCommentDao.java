package com.cmri.um.he.index.appview.dao;

import com.cmri.um.he.index.appview.mapper.AppCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lch
 * Created on 2018/06/13 11:25
 */
@Repository
public class AppCommentDao extends BaseDao{
    @Autowired
    private AppCommentMapper mapper;

    /**
     * 查询指定月份、指定类别的应用数
     */
    public int countOfCategory(String month, int category) {
        return mapper.countOfCategory(month, category);
    }

    /**
     * 分页查询指定月份、指定类别的应用的评论
     */
    public List<Map<String, Object>> findByCategory(String month, int category, int page, int step) {
        return addNo(mapper.findByCategory(month, category, offset(page, step), step));
    }

    /**
     * 为记录添加序号
     */
    private List<Map<String, Object>> addNo(List<Map<String, Object>> comments) {
        for (int i = 0; i < comments.size(); i++) {
            comments.get(i).put("no", i);
        }
        return comments;
    }
}
