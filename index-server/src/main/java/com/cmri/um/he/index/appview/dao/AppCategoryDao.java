package com.cmri.um.he.index.appview.dao;

import com.cmri.um.he.index.appview.mapper.AppCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 应用类别的数据库访问
 *
 * @author lch
 * Created on 2018/6/13
 */
@Repository
public class AppCategoryDao extends BaseDao {
    @Autowired
    private AppCategoryMapper mapper;

    /**
     * 查询所有应用类别(不带id为0的全部)
     */
    public List<Map<String, Object>> getCategory() {
        return mapper.getCategory();
    }

    /**
     * 查询所有应用类别
     */
    public List<Map<String, Object>> find() {
        return mapper.findCategory();
    }

    /**s
     * 查询应用类别及对应的app数据
     */
    public List<Map<String, Object>> finds() {
        return mapper.findCategorys();
    }

    /**
     * 根据应用类别查询app
     * @param category
     * @return
     */
    public List<Map<String, Object>> findAppByCategory(Integer category) {
        return mapper.findAppByCategory(category);
    }


    /**
     * 查询有数据的口碑对应的category和 app
     * @param category
     * @return
     */
    public List<Map<String, Object>> getCategoryByEmotion(Integer category) {
        return mapper.getCategoryByEmotion(category);
    }
}
