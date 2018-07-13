package com.cmri.um.he.index.appview.dao;

import com.cmri.um.he.index.appview.mapper.AppMauMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 应用月活的数据库访问
 *
 * @author zhuyin
 * Created on 2018/6/4
 */
@Repository
public class AppMauDao extends BaseDao {
    @Autowired
    private AppMauMapper mapper;

    /**
     * 查询指定月份的应用数
     */
    public int count(String month) {
        return mapper.count(month);
    }

    /**
     * 查询指定月份、指定类别的应用数
     */
    public int countOfCategory(String month, String category) {
        return mapper.countOfCategory(month, category);
    }

    /**
     * 分页查询指定月份的所有应用的月活
     */
    public List<Map<String, Object>> find(String month, int page, int step) {
        return addNo(mapper.find(month, offset(page, step), step));
    }

    /**
     * 分页查询指定月份、指定类别的应用的月活
     */
    public List<Map<String, Object>> findByCategory(String month, String category, int page, int step) {
        return addNo(mapper.findByCategory(month, category, offset(page, step), step));
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
