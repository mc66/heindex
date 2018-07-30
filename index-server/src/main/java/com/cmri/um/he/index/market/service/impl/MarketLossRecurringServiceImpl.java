package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.market.dao.MarketLossRecurringDao;
import com.cmri.um.he.index.market.mapper.MarketLossRecurringMapper;
import com.cmri.um.he.index.market.service.MarketLossRecurringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        if (status.equals("week")){
            return marketLossRecurringDao.weekStatisticalTable(app,startTime,endTime);
        }else if (status.equals("month")){
            if(startTime==null){
                int startTimes= Integer.parseInt(endTime);
                startTimes=startTimes-Constants.DEFAULT_MONTH;
                startTime=Integer.toString(startTimes);
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
        if (status.equals("week")){
            return marketLossRecurringDao.weekUserRecurring(app,startTime,endTime);
        }else if (status.equals("month")){
            if(startTime==null){
                int startTimes= Integer.parseInt(endTime);
                startTimes=startTimes-Constants.DEFAULT_MONTH;
                startTime=Integer.toString(startTimes);
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
        if (status.equals("week")){
            return marketLossRecurringDao.weekUserActive(app,startTime,endTime);
        }else if (status.equals("month")){
            if(startTime==null){
                int startTimes= Integer.parseInt(endTime);
                startTimes=startTimes-Constants.DEFAULT_MONTH;
                startTime=Integer.toString(startTimes);
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
    public Map<String, Object> quaryLossRecurringExponent(Integer app, String month) {
        int startTimes= Integer.parseInt(month);
        startTimes=startTimes-Constants.CONTRAST_MONTH;
        String contrastMonth =Integer.toString(startTimes);
        Map<String, Object> map = marketLossRecurringDao.quaryLossRecurringExponent(app,month);
        Map<String, Object>  map1 = marketLossRecurringDao.quaryLossRecurringExponent(app,contrastMonth);
        if ((double)map.get("lossNum")>(double)map1.get("lossNum")){
            map.put("lossNum-status",Constants.STATUS_RISE);
        }else {
            map.put("lossNum-status",Constants.STATUS_DECLINE);
        }
        if ((double)map.get("lossRate")>(double)map1.get("lossRate")){
            map.put("lossRate-status",Constants.STATUS_RISE);
        }else {
            map.put("lossRate-status",Constants.STATUS_DECLINE);
        }
        if ((double)map.get("recurringNum")>(double)map1.get("recurringNum")){
            map.put("recurringNum-status",Constants.STATUS_RISE);
        }else {
            map.put("recurringNum-status",Constants.STATUS_DECLINE);
        }
        if ((double)map.get("recurringRate")>(double)map1.get("recurringRate")){
            map.put("recurringRate-status",Constants.STATUS_RISE);
        }else {
            map.put("recurringRate-status",Constants.STATUS_DECLINE);
        }
        return map;
    }
}
