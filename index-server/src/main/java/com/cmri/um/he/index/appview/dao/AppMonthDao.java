package com.cmri.um.he.index.appview.dao;

import com.cmri.um.he.index.appview.mapper.AppMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 报告期数的数据库访问
 *
 * @author yinjunjun
 * Created on 2018/6/15
 */
@Repository
public class AppMonthDao extends BaseDao{
    @Autowired
    private AppMonthMapper mapper;

    /**
     * 查询所有报告期数
     * */
    public List<Map<String,Object>> findMonths(){
        return mapper.findMonths();
    }

    /**
     * 查询体验指数所有报告期数
     * */
    public List<Map<String,Object>> findExperienceMonths(){
        return mapper.findExperienceMonths();
    }
}
