package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.MarketUserRetentiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 市场指数的用户留存页面
 * @author machao
 * Created on 2018/7/31
 */
@Repository
public class MarketUserRetentiveDao extends BaseDao{

    @Autowired
    private MarketUserRetentiveMapper marketUserRetentiveMapper;

    /**
     * 查询周的用户留存数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> weekUserRetentiveNum(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.weekUserRetentiveNum(app, startTime, endTime);
    }
    /**
     * 查询月的用户留存数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> monthUserRetentiveNum(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.monthUserRetentiveNum(app, startTime, endTime);
    }
    /**
     * 查询日的用户留存数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> dayUserRetentiveNum(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.dayUserRetentiveNum(app, startTime, endTime);
    }

    /**
     * 查询月用户留存指数
     * @param app
     * @param month
     * @return
     */
    public Map<String, Object>  quaryUserRetentiveExponent(Integer app, String month){
        return marketUserRetentiveMapper.quaryUserRetentiveExponent(app,month);
    }



    /**
     * 查询周的用户留存情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> weekUserRetentiveTable(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.weekUserRetentiveTable(app, startTime, endTime);
    }
    /**
     * 查询月的用户留存情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> monthUserRetentiveTable(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.monthUserRetentiveTable(app, startTime, endTime);
    }
    /**
     * 查询日的用户留存情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String, Object>> dayUserRetentiveTable(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.dayUserRetentiveTable(app, startTime, endTime);
    }

    /**
     * 查询周的时间范围
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> weekMonthList(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.weekMonthList(app,startTime,endTime);
    }
    /**
     * 查询月的时间范围
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> monthMonthList(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.monthMonthList(app,startTime,endTime);
    }
    /**
     * 查询日的时间范围
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> dayMonthList(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.dayMonthList(app,startTime,endTime);
    }

    /**
     * 查询周的活跃用户数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Double> weekUserActiveList(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.weekUserActiveList(app,startTime,endTime);
    }
    /**
     * 查询月的活跃用户数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Double> monthUserActiveList(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.monthUserActiveList(app,startTime,endTime);
    }
    /**
     * 查询日的活跃用户数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Double> dayUserActiveList(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.dayUserActiveList(app,startTime,endTime);
    }

    /**
     * 查询周的留存率
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> weekRetentiveRateList(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.weekRetentiveRateList(app,startTime,endTime);
    }
    /**
     * 查询月的留存率
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> monthRetentiveRateList(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.monthRetentiveRateList(app,startTime,endTime);
    }
    /**
     * 查询日的留存率
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    public List<String> dayRetentiveRateList(Integer app, String startTime, String endTime){
        return marketUserRetentiveMapper.dayRetentiveRateList(app,startTime,endTime);
    }
}
