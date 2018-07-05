package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.AppCategoryMapper;
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
}
