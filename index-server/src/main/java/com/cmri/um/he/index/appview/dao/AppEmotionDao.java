package com.cmri.um.he.index.appview.dao;

import com.cmri.um.he.index.appview.mapper.AppEmotionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 某类情感得分的数据库访问
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@Repository
public class AppEmotionDao extends BaseDao {

    @Autowired
    private AppEmotionMapper mapper;

    /**
     * 查询指定类别、指定月份的应用情感得分
     *
     * */
    public List<Map<String, Object>> findByCategory(Integer category) {
        return mapper.findByCategory(category);
    }
}
