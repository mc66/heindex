package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.AppMarketMapper;
import com.cmri.um.he.index.market.mapper.MarketLossRecurringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 市场指数的流失与回流页面
 * @author machao
 * Created on 2018/7/27
 */
@Repository
public class MarketLossRecurringDao extends BaseDao{

    @Autowired
    private MarketLossRecurringMapper marketLossRecurringMapper;

    /**
     * 查询周的用户流失情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> weekUserActive(Integer app, String startTime, String endTime){
        return marketLossRecurringMapper.weekUserActive(app, startTime, endTime);
    }
    /**
     * 查询月的用户流失情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> monthUserActive(Integer app, String startTime, String endTime){
        return marketLossRecurringMapper.monthUserActive(app, startTime, endTime);
    }
    /**
     * 查询日的用户流失情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> dayUserActive(Integer app, String startTime, String endTime){
        return marketLossRecurringMapper.dayUserActive(app, startTime, endTime);
    }

    /**
     * 查询月流失回流指数
     * @param app
     * @param month
     * @return
     */
    public Map<String, Object>  quaryLossRecurringExponent(Integer app, String month){
        return marketLossRecurringMapper.quaryLossRecurringExponent(app,month);
    }

    /**
     * 查询周的用户回流情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> weekUserRecurring(Integer app, String startTime, String endTime){
        return marketLossRecurringMapper.weekUserRecurring(app, startTime, endTime);
    }
    /**
     * 查询月的用户回流情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> monthUserRecurring(Integer app, String startTime, String endTime){
        return marketLossRecurringMapper.monthUserRecurring(app, startTime, endTime);
    }
    /**
     * 查询日的用户回流情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> dayUserRecurring(Integer app, String startTime, String endTime){
        return marketLossRecurringMapper.dayUserRecurring(app, startTime, endTime);
    }

    /**
     * 查询周的用户情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> weekStatisticalTable(Integer app, String startTime, String endTime){
        return marketLossRecurringMapper.weekStatisticalTable(app, startTime, endTime);
    }
    /**
     * 查询月的用户情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> monthStatisticalTable(Integer app, String startTime, String endTime){
        return marketLossRecurringMapper.monthStatisticalTable(app, startTime, endTime);
    }
    /**
     * 查询日的用户情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> dayStatisticalTable(Integer app, String startTime, String endTime){
        return marketLossRecurringMapper.dayStatisticalTable(app, startTime, endTime);
    }
}
