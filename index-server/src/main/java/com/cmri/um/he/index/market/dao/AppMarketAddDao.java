package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.AppMarketAddMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 默认展示时获取查询时间通用方法
 * @author shihao
 * Created on 2018/8/1
 */
@Repository
public class AppMarketAddDao {

    @Autowired
    private AppMarketAddMapper mapper;

    /**
     * 应用新增页面本月数据展示
     * @param category
     * @param app
     * @param month
     * @return
     */
    public List<Map<String, Object>> getAppNumber(Integer category, Integer app, String month){
        return mapper.getAppNumber(category,app,month);
    }

    /**
     * 应用新增页面新增活跃用户展示(根据月份区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> getAppUserByMonth(Integer app, String startTime, String endTime){
        return mapper.getAppUserByMonth(app,startTime,endTime);
    }
    /**
     * 应用新增页面新增活跃用户展示(根据周区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> getAppUserByWeek(Integer app, String startTime, String endTime){
        return mapper.getAppUserByWeek(app,startTime,endTime);
    }
    /**
     * 应用新增页面新增活跃用户展示(根据日区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> getAppUserByDate(Integer app, String startTime, String endTime){
        return mapper.getAppUserByDate(app,startTime,endTime);
    }
    /**
     * 应用新增页面新增次月留存率展示(根据月区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> getAddRateByMonth(Integer app, String startTime, String endTime){
        return mapper.getAddRateByMonth(app,startTime,endTime);
    }
    /**
     * 应用新增页面新增次月留存率展示(根据周区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> getAddRateByWeek(Integer app, String startTime, String endTime){
        return mapper.getAddRateByWeek(app,startTime,endTime);
    }
    /**
     * 应用新增页面新增次月留存率展示(根据日区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> getAddRateByDate(Integer app, String startTime, String endTime){
        return mapper.getAddRateByDate(app,startTime,endTime);
    }


    /**
     * 应用新增页面用户新增数据统计表展示(根据月区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> getCountNumberByMonth(Integer app, String startTime, String endTime){
        return mapper.getCountNumberByMonth(app,startTime,endTime);
    }
    /**
     * 应用新增页面用户新增数据统计表展示(根据周区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> getCountNumberByWeek(Integer app, String startTime, String endTime){
        return mapper.getCountNumberByWeek(app,startTime,endTime);
    }
    /**
     * 应用新增页面用户新增数据统计表展示(根据日区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> getCountNumberByDate(Integer app, String startTime, String endTime){
        return mapper.getCountNumberByDate(app,startTime,endTime);
    }

}
