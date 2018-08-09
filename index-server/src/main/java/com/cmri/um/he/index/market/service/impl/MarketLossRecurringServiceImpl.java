package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.market.dao.MarketLossRecurringDao;
import com.cmri.um.he.index.market.service.MarketLossRecurringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 市场指数的流失与回流页面
 * @author machao
 * Created on 2018/7/27
 */
@Service
public class MarketLossRecurringServiceImpl implements MarketLossRecurringService{
    @Autowired
    private MarketLossRecurringDao marketLossRecurringDao;

    /**
     * 查询用户情况统计表
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryStatisticalTable(String status, Integer app, String startTime, String endTime) {
        if ("week".equals(status)){
            return marketLossRecurringDao.weekStatisticalTable(app,startTime,endTime);
        }else if ("month".equals(status)){
            if("null".equals(startTime) || "null".equals(endTime)){
                try {
                    startTime = DefaultTime.getDefaultTimes(Constants.YEAR, Constants.DEFAULT_YEAR,endTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return marketLossRecurringDao.monthStatisticalTable(app,startTime,endTime);
        }else {
            return marketLossRecurringDao.dayStatisticalTable(app,startTime,endTime);
        }
    }


    /**
     * 查询用户回流情况
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryUserRecurring(String status, Integer app, String startTime, String endTime) {
        if ("week".equals(status)){
            return marketLossRecurringDao.weekUserRecurring(app,startTime,endTime);
        }else if ("month".equals(status)){
            if("null".equals(startTime) || "null".equals(endTime)){
                try {
                    startTime = DefaultTime.getDefaultTimes(Constants.YEAR, Constants.DEFAULT_YEAR,endTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return marketLossRecurringDao.monthUserRecurring(app,startTime,endTime);
        }else {
            return marketLossRecurringDao.dayUserRecurring(app,startTime,endTime);
        }
    }

    /**
     * 查询用户流失情况
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryUserLoss(String status, Integer app, String startTime, String endTime) {
        if ("week".equals(status)){
            return marketLossRecurringDao.weekUserActive(app,startTime,endTime);
        }else if ("month".equals(status)){
            if("null".equals(startTime) || "null".equals(endTime)){
                try {
                    startTime = DefaultTime.getDefaultTimes(Constants.YEAR, Constants.DEFAULT_YEAR,endTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return marketLossRecurringDao.monthUserActive(app,startTime,endTime);
        }else {
            return marketLossRecurringDao.dayUserActive(app,startTime,endTime);
        }
    }

    /**
     * 查询月流失回流指数
     * @param app
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryLossRecurringExponent(Integer app, String month) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            String contrastMonth = DefaultTime.getDefaultTimes(Constants.MONTH, Constants.CONTRAST_MONTH,month);
            Map<String, Object> map = marketLossRecurringDao.quaryLossRecurringExponent(app,month);
            Map<String, Object>  map1 = marketLossRecurringDao.quaryLossRecurringExponent(app,contrastMonth);
            if (map==null||map.size()==0){
                list.add(map);
            }else if (map1==null||map1.size()==0){
                map.put("lossNumStatus",Constants.STATUS_RISE);
                map.put("lossRateStatus",Constants.STATUS_RISE);
                map.put("recurringNumStatus",Constants.STATUS_RISE);
                map.put("recurringRateStatus",Constants.STATUS_RISE);
                list.add(map);
            } else {
                if ((double) map.get("lossNum") >= (double) map1.get("lossNum")) {
                    map.put("lossNumStatus", Constants.STATUS_RISE);
                } else {
                    map.put("lossNumStatus", Constants.STATUS_DECLINE);
                }
                if ((double) map.get("lossRate") >= (double) map1.get("lossRate")) {
                    map.put("lossRateStatus", Constants.STATUS_RISE);
                } else {
                    map.put("lossRateStatus", Constants.STATUS_DECLINE);
                }
                if ((double) map.get("recurringNum") >= (double) map1.get("recurringNum")) {
                    map.put("recurringNumStatus", Constants.STATUS_RISE);
                } else {
                    map.put("recurringNumStatus", Constants.STATUS_DECLINE);
                }
                if ((double) map.get("recurringRate") >= (double) map1.get("recurringRate")) {
                    map.put("recurringRateStatus", Constants.STATUS_RISE);
                } else {
                    map.put("recurringRateStatus", Constants.STATUS_DECLINE);
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
