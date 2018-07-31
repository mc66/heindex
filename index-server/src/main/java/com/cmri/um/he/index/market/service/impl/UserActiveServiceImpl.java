package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.market.dao.UserActiveDao;
import com.cmri.um.he.index.market.service.UserActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserActiveServiceImpl implements UserActiveService {

    @Autowired
    private UserActiveDao userActiveDao;

    @Override
    public List<Map<String, Object>> getActiveMonthData(int category, int app, String month) {
        List<Map<String, Object>>  list=userActiveDao.getActiveMonthData(category,app,month);
        if (list.size() ==0){
            return list;
        }else {
            int month1 = Integer.parseInt(month);
            month1 = month1 - 1;
            String month2 = Integer.toString(month1);
            List<Map<String, Object>> list1 = userActiveDao.getActiveMonthData(category, app, month2);
            Map<String, Object> map = list.get(0);
            if (list.size() != 0 && list1.size() != 0) {
                Map<String, Object> map1 = list1.get(0);
                int total_us = (int) map.get("total_user");
                int new_us = (int) map.get("new_user");
                double mau_number = (double) map.get("mau_number");
                double length_time = (double) map.get("length_time");
                double flow = (double) map.get("flow");
                double keep_rate = (double) map.get("keep_rate");
                int total_us1 = (int) map1.get("total_user");
                int new_us1 = (int) map1.get("new_user");
                double mau_number1 = (double) map1.get("mau_number");
                double length_time1 = (double) map1.get("length_time");
                double flow1 = (double) map1.get("flow");
                double keep_rate1 = (double) map1.get("keep_rate");
                if (total_us > total_us1) {
                    map.put("total_usState", 1);
                } else {
                    map.put("total_usState", 0);
                }
                if (new_us > new_us1) {
                    map.put("new_usState", 1);
                } else {
                    map.put("new_usState", 0);
                }
                if (mau_number > mau_number1) {
                    map.put("mau_numberState", 1);
                } else {
                    map.put("mau_numberState", 0);
                }
                if (length_time > length_time1) {
                    map.put("length_timeState", 1);
                } else {
                    map.put("length_timeState", 0);
                }
                if (flow > flow1) {
                    map.put("flowState", 1);
                } else {
                    map.put("flowState", 0);
                }
                if (keep_rate > keep_rate1) {
                    map.put("keep_rateState", 1);
                } else {
                    map.put("keep_rateState", 0);
                }
            } else {
                map.put("total_usState", 1);
                map.put("new_usState", 1);
                map.put("mau_numberState", 1);
                map.put("length_timeState", 1);
                map.put("flowState", 1);
                map.put("keep_rateState", 1);
            }
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> queryActivityList(String month1, int app, String month2, String status) {
        if(status.equals("month")){
            if(month1.equals("null")){
                int month = Integer.parseInt(month2);
                month=month-100;
                month1=Integer.toString(month);
            }
            return   userActiveDao.queryActivityMonthList(app,month1,month2);
        }else if(status.equals("week")){
            return   userActiveDao.queryActivityWeekList(app,month1,month2);
        } else {
            return   userActiveDao.queryActivityDayList(app,month1,month2);
        }

    }


    @Override
    public List<Map<String, Object>> queryNumberDayList(String month1, int app, String month2, String state) {
        if(state.equals("month")){
            if(month1.equals("null")){
                int month = Integer.parseInt(month2);
                month=month-100;
                month1=Integer.toString(month);
            }
            return   userActiveDao.queryNumberDayMonthList(app,month1,month2);
        }else if(state.equals("week")){
            return   userActiveDao.queryNumberDayWeekList(app,month1,month2);
        } else {
            return   userActiveDao.queryNumberDayDayList(app,month1,month2);
        }
    }

    @Override
    public List<Map<String, Object>> queryBehaviorList(String month1, int app, String month2, String state) {
        if(state.equals("month")){
            if(month1.equals("null")){
                int month = Integer.parseInt(month2);
                month=month-100;
                month1=Integer.toString(month);
            }
            return   userActiveDao.queryBehaviorMonthList(app,month1,month2);
        }else if(state.equals("week")){
            return   userActiveDao.queryBehaviorWeekList(app,month1,month2);
        } else {
            return   userActiveDao.queryBehaviorDayList(app,month1,month2);
        }
    }

    @Override
    public List<Map<String, Object>> queryStatisticalList(String month1, int app, String month2, String state) {
        if(state.equals("month")){
            if(month1.equals("null")){
                int month = Integer.parseInt(month2);
                month=month-100;
                month1=Integer.toString(month);
            }
            return   userActiveDao.queryStatisticalMonthList(app,month1,month2);
        }else if(state.equals("week")){
            return   userActiveDao.queryStatisticalWeekList(app,month1,month2);
        } else {
            return   userActiveDao.queryStatisticalDayList(app,month1,month2);
        }
    }
}
