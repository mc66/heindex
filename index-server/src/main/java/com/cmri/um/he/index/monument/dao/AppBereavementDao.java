package com.cmri.um.he.index.monument.dao;

import com.cmri.um.he.index.monument.mapper.AppBereavementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 情感指数变化趋势的数据库访问
 *
 * @author yinjunjun
 * Created on 2018/8/10
 */
@Repository
public class AppBereavementDao extends BaseDao{

    @Autowired
    private AppBereavementMapper mapper;

    /**
     * 查询指定月份区间的情感趋势变化
     * */
    public List<Map<String,Object>> findBereavement(Integer category,String startTime,String endTime){
        return mapper.findBereavement(category, startTime, endTime);
    }

    /**
     * 查询指定月份的情感指数
     * */
    public List<Map<String,Object>> findMoonEmotion(Integer category,String startTime){
        return mapper.findMoonEmotion(category, startTime);
    }


    /**
     * 查询指定类下的所有app
     * */
    public List<String> findCategory(Integer category){
        return mapper.findCategory(category);
    }
}
