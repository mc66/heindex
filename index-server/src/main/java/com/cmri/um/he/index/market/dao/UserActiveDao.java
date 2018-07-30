package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.UserActiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserActiveDao extends BaseDao{

    @Autowired
    private UserActiveMapper mapper;

    public List<Map<String,Object>> getActiveMonthData(int category, int app, String month) {
        return mapper.getActiveMonthData(category,app,month);
    }

    public List<Map<String,Object>> queryActivityMonthList(int app, String month1, String month2) {
        return mapper.queryActivityMonthList(month1,app,month2);
    }

    public List<Map<String,Object>> queryActivityWeekList(int app, String month1, String month2) {
        return mapper.queryActivityWeekList(month1,app,month2);
    }

    public List<Map<String,Object>> queryActivityDayList(int app, String month1, String month2) {
        return mapper.queryActivityDayList(month1,app,month2);
    }

    public List<Map<String,Object>> queryNumberDayMonthList(int app, String month1, String month2) {
        return mapper.queryNumberDayMonthList(month1,app,month2);
    }

    public List<Map<String,Object>> queryNumberDayWeekList(int app, String month1, String month2) {
        return mapper.queryNumberDayWeekList(month1,app,month2);
    }

    public List<Map<String,Object>> queryNumberDayDayList(int app, String month1, String month2) {
        return mapper.queryNumberDayDayList(month1,app,month2);
    }

    public List<Map<String,Object>> queryBehaviorMonthList(int app, String month1, String month2) {
        return mapper.queryBehaviorMonthList(month1,app,month2);
    }

    public List<Map<String,Object>> queryBehaviorWeekList(int app, String month1, String month2) {
        return mapper.queryBehaviorWeekList(month1,app,month2);
    }

    public List<Map<String,Object>> queryBehaviorDayList(int app, String month1, String month2) {
        return mapper.queryBehaviorDayList(month1,app,month2);
    }

    public List<Map<String,Object>> queryStatisticalMonthList(int app, String month1, String month2) {
        return mapper.queryStatisticalMonthList(month1,app,month2);
    }

    public List<Map<String,Object>> queryStatisticalWeekList(int app, String month1, String month2) {
        return mapper.queryStatisticalWeekList(month1,app,month2);
    }

    public List<Map<String,Object>> queryStatisticalDayList(int app, String month1, String month2) {
        return mapper.queryStatisticalDayList(month1,app,month2);
    }
}
