package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.market.dao.UserTrendsDao;
import com.cmri.um.he.index.market.service.UserTrendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserTrendsServiceImpl implements UserTrendsService {

    @Autowired
    private UserTrendsDao userTrendsDao;

    @Override
    public List<Map<String, Object>> queryMonthsDataList(int category, int app, String month) {
        List<Map<String, Object>>  list=null;
        try {
            list=userTrendsDao.queryMonthsDataList(category,app,month);
            if (list.size() ==0){
                return list;
            }else {
                String defaultTime = DefaultTime.getDefaultTimes(Constants.MONTH, 1, month);
                List<Map<String, Object>> list1 = userTrendsDao.queryMonthsDataList(category, app, defaultTime);
                Map<String, Object> map = list.get(0);
                if (list.size() != 0 && list1.size() != 0) {
                    Map<String, Object> map1 = list1.get(0);
                    double totaLus = (double) map.get("total_user");
                    double penetrationRate = (double) map.get("penetration_rate");
                    double totaLus1 = (double) map1.get("total_user");
                    double penetrationRate1 = (double) map1.get("penetration_rate");
                    if (totaLus > totaLus1) {
                        map.put("totaLusState", 1);
                    } else {
                        map.put("totaLusState", 0);
                    }
                    if (penetrationRate > penetrationRate1) {
                        map.put("penetrationRateState", 1);
                    } else {
                        map.put("penetrationRateState", 0);
                    }
                }else {
                    map.put("totaLusState", 1);
                    map.put("penetrationRateState", 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> queryPenetrationList(int app, String month1, String month2, String status) {
        if(status.equals("month")){
            if("null".equals(month1) || "null".equals(month2)){
                try {
                    month1 = DefaultTime.getDefaultTimes(Constants.YEAR, Constants.DEFAULT_YEAR,month2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return   userTrendsDao.queryPenetrationMonthList(app,month1,month2);
        }else if(status.equals("week")){
            return   userTrendsDao.queryPenetrationWeekList(app,month1,month2);
        } else {
            return   userTrendsDao.queryPenetrationDayList(app,month1,month2);
        }
    }

    @Override
    public List<Map<String, Object>> queryUserStatisticsList(int app, String month1, String month2, String status) {
        if(status.equals("month")){
            if("null".equals(month1) || "null".equals(month2)){
                try {
                    month1 = DefaultTime.getDefaultTimes(Constants.YEAR, Constants.DEFAULT_YEAR,month2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return   userTrendsDao.queryUserStatisticsMonthList(app,month1,month2);
        }else if(status.equals("week")){
            return   userTrendsDao.queryUserStatisticsWeekList(app,month1,month2);
        } else {
            return   userTrendsDao.queryUserStatisticsDayList(app,month1,month2);
        }
    }
}
