package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.AppProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 查询app某一具体维度的问题列表
 * Created by machao on 2018/7/3.
 */
@Repository
public class AppProblemDao extends BaseDao{

    @Autowired
    AppProblemMapper appProblemMapper;

    /**
     * 根据应用以及维度查询问题列表
     * @param app
     * @param dimensions
     * @return
     */
    public List<Map<String, Object>> getAppProblem(int app,String dimensions,String month) {
        return appProblemMapper.getAppProblem(app,dimensions,month);
    }
}
