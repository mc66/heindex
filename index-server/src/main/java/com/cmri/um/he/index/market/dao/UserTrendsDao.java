package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.UserTrendsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserTrendsDao {

    @Autowired
    private UserTrendsMapper mapper;

    public List<Map<String,Object>> queryMonthsDataList(int category, int app, String month) {
        return mapper.queryMonthsDataList(category,app,month);
    }

    public List<Map<String,Object>> queryPenetrationMonthList(int app, String month1, String month2) {
        return mapper.queryPenetrationMonthList(month1,app,month2);
    }

    public List<Map<String,Object>> queryPenetrationWeekList(int app, String month1, String month2) {
        return mapper.queryPenetrationWeekList(month1,app,month2);
    }

    public List<Map<String,Object>> queryPenetrationDayList(int app, String month1, String month2) {
        return mapper.queryPenetrationDayList(month1,app,month2);
    }

    public List<Map<String,Object>> queryUserStatisticsMonthList(int app, String month1, String month2) {
        return mapper.queryUserStatisticsMonthList(month1,app,month2);
    }

    public List<Map<String,Object>> queryUserStatisticsWeekList(int app, String month1, String month2) {
        return mapper.queryUserStatisticsWeekList(month1,app,month2);
    }

    public List<Map<String,Object>> queryUserStatisticsDayList(int app, String month1, String month2) {
        return mapper.queryUserStatisticsDayList(month1,app,month2);
    }
}
