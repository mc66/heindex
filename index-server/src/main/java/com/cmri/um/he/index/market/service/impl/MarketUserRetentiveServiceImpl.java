package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.market.dao.MarketUserRetentiveDao;
import com.cmri.um.he.index.market.service.MarketLossRecurringService;
import com.cmri.um.he.index.market.service.MarketUserRetentiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 市场指数的用户留存页面
 * @author machao
 * Created on 2018/7/31
 */
@Service
public class MarketUserRetentiveServiceImpl implements MarketUserRetentiveService{
    @Autowired
    private MarketUserRetentiveDao marketUserRetentiveDao;

    /**
     * 查询用户留存情况统计表
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryUserRetentiveTable(String status, Integer app, String startTime, String endTime) {
        if ("week".equals(status)){
            return marketUserRetentiveDao.weekUserRetentiveTable(app,startTime,endTime);
        }else if ("month".equals(status)){
            if("null".equals(startTime) || "null".equals(endTime)){
                Map<String, String> defaultTime = DefaultTime.getDefaultTime(Constants.YEAR, Constants.DEFAULT_YEAR);
                startTime= defaultTime.get("startTime");
                endTime=defaultTime.get("endTime");
            }
            return marketUserRetentiveDao.monthUserRetentiveTable(app,startTime,endTime);
        }else {
            return marketUserRetentiveDao.dayUserRetentiveTable(app,startTime,endTime);
        }
    }


    /**
     * 查询用户留存率
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public Map<String, Object> quaryUserRetentiveRate(String status, Integer app, String startTime, String endTime) {
        Map<String, Object> map =new HashMap<>(4);
        if ("week".equals(status)){

            String[] title={"时间","活跃用户数(万)","第2周","第3周","第4周","第5周","第6周","第7周","第8周","第9周"};
            List<String> month = marketUserRetentiveDao.weekMonthList(app,startTime,endTime);
            List<Double> userActive = marketUserRetentiveDao.weekUserActiveList(app,startTime,endTime);
            List<List<Double>> retentiveRate=new ArrayList(month.size());
            for(int i=0;i<month.size();i++){
                startTime=month.get(i);
                List<Double> list= marketUserRetentiveDao.weekRetentiveRateList(app,startTime,endTime);
                retentiveRate.add(i,list);
            }
            map.put("title",title);
            map.put("month",month);
            map.put("userActive",userActive);
            map.put("retentiveRate",retentiveRate);
            return map;
        }else if ("month".equals(status)){
            if("null".equals(startTime) || "null".equals(endTime)){
                Map<String, String> defaultTime = DefaultTime.getDefaultTime(Constants.YEAR, Constants.DEFAULT_YEAR);
                startTime= defaultTime.get("startTime");
                endTime=defaultTime.get("endTime");
            }

            String[] title={"时间","活跃用户数(万)","第2月","第3月","第4月","第5月","第6月","第7月","第8月","第9月"};
            List<String> month = marketUserRetentiveDao.monthMonthList(app,startTime,endTime);
            List<Double> userActive = marketUserRetentiveDao.monthUserActiveList(app,startTime,endTime);
            List<List<Double>> retentiveRate=new ArrayList(month.size());
            for(int i=0;i<month.size();i++){
                startTime=month.get(i);
                List<Double> list= marketUserRetentiveDao.monthRetentiveRateList(app,startTime,endTime);
                retentiveRate.add(i,list);
            }
            map.put("title",title);
            map.put("month",month);
            map.put("userActive",userActive);
            map.put("retentiveRate",retentiveRate);
            return map;
        }else {
            String[] title={"时间","活跃用户数(万)","第2日","第3日","第4日","第5日","第6日","第7日","第15日","第30日"};
            List<String> month = marketUserRetentiveDao.dayMonthList(app,startTime,endTime);
            List<Double> userActive = marketUserRetentiveDao.dayUserActiveList(app,startTime,endTime);
            List<List<Double>> retentiveRate=new ArrayList(month.size());
            for(int i=0;i<month.size();i++){
                startTime=month.get(i);
                List<Double> list= marketUserRetentiveDao.dayRetentiveRateList(app,startTime,endTime);
                retentiveRate.add(i,list);
            }
            map.put("title",title);
            map.put("month",month);
            map.put("userActive",userActive);
            map.put("retentiveRate",retentiveRate);
            return map;
        }
    }

    /**
     * 查询用户留存数
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryUserRetentiveNum(String status, Integer app, String startTime, String endTime) {
        if ("week".equals(status)){
            return marketUserRetentiveDao.weekUserRetentiveNum(app,startTime,endTime);
        }else if ("month".equals(status)){
            if("null".equals(startTime) || "null".equals(endTime)){
                Map<String, String> defaultTime = DefaultTime.getDefaultTime(Constants.YEAR, Constants.DEFAULT_YEAR);
                startTime= defaultTime.get("startTime");
                endTime=defaultTime.get("endTime");
            }
            return marketUserRetentiveDao.monthUserRetentiveNum(app,startTime,endTime);
        }else {
            return marketUserRetentiveDao.dayUserRetentiveNum(app,startTime,endTime);
        }
    }

    /**
     * 查询月用户留存市场指数
     * @param app
     * @param month
     * @return
     */
    @Override
    public Map<String, Object> quaryUserRetentiveExponent(Integer app, String month) {
        int startTimes= Integer.parseInt(month);
        startTimes=startTimes-Constants.CONTRAST_MONTH;
        String contrastMonth =Integer.toString(startTimes);
        Map<String, Object> map = marketUserRetentiveDao.quaryUserRetentiveExponent(app,month);
        Map<String, Object>  map1 = marketUserRetentiveDao.quaryUserRetentiveExponent(app,contrastMonth);
        if ((double)map.get("retentionNum")>(double)map1.get("retentionNum")){
            map.put("retentionNumStatus",Constants.STATUS_RISE);
        }else {
            map.put("retentionNumStatus",Constants.STATUS_DECLINE);
        }
        if ((double)map.get("retentionRate")>(double)map1.get("retentionRate")){
            map.put("retentionRateStatus",Constants.STATUS_RISE);
        }else {
            map.put("retentionRateStatus",Constants.STATUS_DECLINE);
        }
        return map;
    }
}
