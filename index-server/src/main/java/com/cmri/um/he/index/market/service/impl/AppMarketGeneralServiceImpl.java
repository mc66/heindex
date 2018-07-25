package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.market.dao.AppMarketGeneralDao;
import com.cmri.um.he.index.market.service.AppMarketGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppMarketGeneralServiceImpl implements AppMarketGeneralService{

    @Autowired
    private AppMarketGeneralDao appMarketGeneralDao;

    @Override
    public List<Map<String, Object>> getAppMarketList(int category, int app, String month) {
        List<Map<String, Object>>  list=appMarketGeneralDao.getMarketMonth(category,app,month);
        if (list.size() ==0){
            return list;
        }else {
                int month1 = Integer.parseInt(month);
                month1 = month1 - 1;
                String month2 = Integer.toString(month1);
                List<Map<String, Object>> list1 = appMarketGeneralDao.getMarketMonth1(category, app, month2);
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
    public List<Map<String, Object>> getCumulative(int app, String month2) {
        int month2s= Integer.parseInt(month2);
        month2s=month2s-11;
        String month1=Integer.toString(month2s);
        return appMarketGeneralDao.getCumulative(app,month1,month2);
    }

    @Override
    public List<Map<String, Object>> getCumulativeList(int app, String month1, String month2) {
        return appMarketGeneralDao.getCumulativeList(app,month1,month2);
    }
}
