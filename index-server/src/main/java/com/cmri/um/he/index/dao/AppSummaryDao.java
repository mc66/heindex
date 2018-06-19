package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.AppSummaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 品质、运营总结的数据库访问
 *
 * @author yinjunjun
 * Created on 2018/6/19
 */
@Repository
public class AppSummaryDao extends BaseDao {

    @Autowired
    private AppSummaryMapper mapper;

    /**
     * 查询指定类别、指定月份的品质总结
     *
     * */
    public List<Map<String,Object>> findQuality(String month, Integer category){
        return mapper.findQuality(month,category);
    }


    /**
     * 查询指定类别、指定月份的运营总结
     *
     * */
    public List<Map<String,Object>> findOperation(String month, Integer category){
        return mapper.findOperation(month,category);
    }
}
