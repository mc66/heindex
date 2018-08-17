package com.cmri.um.he.index.monument.dao;

import com.cmri.um.he.index.monument.mapper.AppBereavementMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    public List<Map<String,Object>> findMoonEmotion(Integer category,String endTime){
        return mapper.findMoonEmotion(category, endTime);
    }


    /**
     * 查询指定类下的所有app
     * */
    public List<Map<String,Object>> findCategory(Integer category){
        return mapper.findCategory(category);
    }

    /**
     * 查询评论热词  词频统计
     * */
    public List<Map<String,Object>> frequencyCount(Integer app,String startTime, String endTime){
        return mapper.frequencyCount(app, startTime, endTime);
    }

    /**
     * 查询app在某月的正中负的评论数量
     * @param category
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public int getFavorableRate(int category,int app, String startTime,String endTime){
        return  mapper.getFavorableRate(category, app, startTime, endTime);
    };
    public int getFavorableRate1(int category,int app, String startTime,String endTime){
        return  mapper.getFavorableRate1(category, app, startTime, endTime);
    };
    public int getFavorableRate2(int category,int app, String startTime,String endTime){
        return  mapper.getFavorableRate2(category, app, startTime, endTime);
    };
    public int getFavorableRate3(int category,int app, String startTime,String endTime){
        return  mapper.getFavorableRate3(category, app, startTime, endTime);
    };

}
