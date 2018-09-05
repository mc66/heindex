package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.market.dao.AppMarketGeneralDao;
import com.cmri.um.he.index.market.service.AppMarketGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 默认展示时获取查询时间通用方法
 * @author shihao
 * Created on 2018/7/30
 */
@Service
public class AppMarketGeneralServiceImpl implements AppMarketGeneralService{

    @Autowired
    private AppMarketGeneralDao appMarketGeneralDao;

    /**
     * 查询人均时长和人均流量
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Override
    public List<Map<String, Object>> getLengthTime(Integer app, String month1, String month2,String status) {
        if(status.equals("month")){
            if (month1.equals("null")) {
                try {
                    String defaultTime = DefaultTime.getDefaultTimes(Constants.YEAR, 1, month2);
                    return appMarketGeneralDao.getLengthTimeBymonth(app,defaultTime,month2);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }else {
                return appMarketGeneralDao.getLengthTimeBymonth(app,month1,month2);
            }

        }else if(status.equals("week")){
            return appMarketGeneralDao.getLengthTimeByWeek(app,month1,month2);
        }else {
            return appMarketGeneralDao.getLengthTimeByDate(app,month1,month2);
        }


    }
    /**
     * 查询应用概括统计
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryGeneralStatistic(Integer app, String month1, String month2 ,String status) {

        if(status.equals("month")){
            if (month1.equals("null")) {
                try {
                    String defaultTime = DefaultTime.getDefaultTimes(Constants.YEAR, 1, month2);
                    return appMarketGeneralDao.quaryGeneralStatisticByMonth(app,defaultTime,month2);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }else {
                return appMarketGeneralDao.quaryGeneralStatisticByMonth(app,month1,month2);
            }

        }else if(status.equals("week")){
            return appMarketGeneralDao.quaryGeneralStatisticByWeek(app,month1,month2);
        }else {
            return appMarketGeneralDao.quaryGeneralStatisticByDate(app,month1,month2);
        }
    }

    /**
     * 查询次月存留率
     * @param app
     * @param month1
     * @param month2
     * @param status
     * @return
     */
    @Override
    public List<Map<String, Object>> getRate(Integer app, String month1, String month2, String status) {
        List<Map<String, Object>> lists=new ArrayList<>();
        Map<String, Object> map =new HashMap<>();
        if(status.equals("month")){
            if (month1.equals("null")){
                try {
                    String defaultTime = DefaultTime.getDefaultTimes(Constants.YEAR, 1, month2);
                    List list=new ArrayList();
                    List<Map<String, Object>> maplist= appMarketGeneralDao.getRateByMonth(app,defaultTime,month2);
                    List listmonth=new ArrayList();
                    List listmau=new ArrayList();
                    for (Map<String, Object> ob : maplist) {
                        Object month = ob.get("month");
                        listmonth.add(month);
                        Object mau_number = ob.get("mau_number");
                        listmau.add(mau_number);
                    }
                    //次月存活率
                    for(int i=0;i<listmonth.size();i++){
                        month1=(String)listmonth.get(i);
                        month2=(String)listmonth.get(0);
                        List<Map<String, Object>> list2= appMarketGeneralDao.getRateByMonth(app,month1,month2);
                        Collections.reverse(list2);
                        List list4=new ArrayList();
                        for (Map<String, Object> obmap : list2) {
                            double rate = (double)obmap.get("keep_rate");
                            String keep_rate =String.valueOf(rate) +"%";
                            list4.add(keep_rate);
                        }
                        list.add(list4);
                    }
                    map.put("rate",list);
                    String[] st={"时间","活跃用户数(万)","第2月","第3月","第4月","第5月","第6月","第7月","第8月","第9月"};
                    map.put("time",listmonth);
                    map.put("user_number",listmau);
                    map.put("title",st);
                    lists.add(map);
                    return lists;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

            }else{
                List list=new ArrayList();
                List<Map<String, Object>> maplist= appMarketGeneralDao.getRateByMonth(app,month1,month2);
                List listmonth=new ArrayList();
                List listmau=new ArrayList();
                for (Map<String, Object> ob : maplist) {
                    Object month = ob.get("month");
                    listmonth.add(month);
                    Object mau_number = ob.get("mau_number");
                    listmau.add(mau_number);
                }
                //次月存活率
                for(int i=0;i<listmonth.size();i++){
                    month1=(String)listmonth.get(i);
                    month2=(String)listmonth.get(0);
                    List<Map<String, Object>> list2= appMarketGeneralDao.getRateByMonth(app,month1,month2);
                    Collections.reverse(list2);
                    List list4=new ArrayList();
                    for (Map<String, Object> obmap : list2) {
                        double rate =(double) obmap.get("keep_rate");
                        String keep_rate =String.valueOf(rate) +"%";
                        list4.add(keep_rate);
                    }
                    list.add(list4);
                }
                String[] st={"时间","活跃用户数(万)","第2月","第3月","第4月","第5月","第6月","第7月","第8月","第9月"};
                map.put("time",listmonth);
                map.put("user_number",listmau);
                map.put("title",st);
                map.put("rate",list);
                lists.add(map);
                return lists;
            }

        }else if(status.equals("week")){
            List list=new ArrayList();
            List<Map<String, Object>> maplist= appMarketGeneralDao.getRateByWeek(app,month1,month2);
            List listweek=new ArrayList();
            List listmau=new ArrayList();
            for (Map<String, Object> ob : maplist) {
                Object week = ob.get("week");
                listweek.add(week);
                Object weeky_active = ob.get("weekly_active");
                listmau.add(weeky_active);
            }
            //次周存活率
            for(int i=0;i<listweek.size();i++){
                month1=(String)listweek.get(i);
                month2=(String)listweek.get(0);
                List<Map<String, Object>> list2= appMarketGeneralDao.getRateByWeek(app,month1,month2);
                Collections.reverse(list2);
                List list4=new ArrayList();
                for (Map<String, Object> obmap : list2) {
                    double rate =(double)obmap.get("active_next_week_retention_rate");
                    String keep_rate =String.valueOf(rate) +"%";
                    list4.add(keep_rate);
                }
                list.add(list4);
            }

            String[] st={"时间","活跃用户数(万)","第2周","第3周","第4周","第5周","第6周","第7周","第8周","第9周"};
            map.put("time",listweek);
            map.put("user_number",listmau);
            map.put("title",st);
            map.put("rate",list);
            lists.add(map);
            return lists;
        }else {
            List list=new ArrayList();
            List<Map<String, Object>> maplist= appMarketGeneralDao.getRateByDate(app,month1,month2);
            List listdate=new ArrayList();
            List listmau=new ArrayList();
            for (Map<String, Object> ob : maplist) {
                Object date = ob.get("date");
                listdate.add(date);
                Object dately_active = ob.get("dately_active");
                listmau.add(dately_active);
            }
            //次日存活率
            for(int i=0;i<listdate.size();i++){
                month1=(String)listdate.get(i);
                month2=(String)listdate.get(0);
                List<Map<String, Object>> list2= appMarketGeneralDao.getRateByDate(app,month1,month2);
                Collections.reverse(list2);
                List list4=new ArrayList();
                for (Map<String, Object> obmap : list2) {
                    double rate = (double)obmap.get("active_next_date_retention_rate");
                    String keep_rate =String.valueOf(rate) +"%";
                    list4.add(keep_rate);
                }
                list.add(list4);
            }
            String[] st={"时间","活跃用户数(万)","第2日","第3日","第4日","第5日","第6日","第7日","第15日","第30日"};
            map.put("time",listdate);
            map.put("user_number",listmau);
            map.put("title",st);
            map.put("rate",list);
            lists.add(map);
            return lists;
        }
    }

    /**
     * 查询月活跃用户
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Override
    public List<Map<String, Object>> getUserNumber(Integer app,String month1,String month2,String status) {
        if(status.equals("month")){
            if (month1.equals("null")) {
                try {
                    String defaultTime = DefaultTime.getDefaultTimes(Constants.YEAR, 1, month2);
                    return   appMarketGeneralDao.getUserNumberByMonth(app,defaultTime,month2);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }else {
                return   appMarketGeneralDao.getUserNumberByMonth(app,month1,month2);
            }

        }else if(status.equals("week")){
            return   appMarketGeneralDao.getUserNumberByWeek(app,month1,month2);
        }else {
            return   appMarketGeneralDao.getUserNumberBydate(app,month1,month2);
        }
    }
    /**
     *  查询当月数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Object>> getAppMarketList(int category, int app, String month){
        List<Map<String, Object>>  list=appMarketGeneralDao.getMarketMonth(category,app,month);
        if (list.size() ==0){
            return list;
        }else {
            try {
                String defaultTime = DefaultTime.getDefaultTimes(Constants.MONTH, 1, month);
                List<Map<String, Object>> list1 = appMarketGeneralDao.getMarketMonth(category, app, defaultTime);
                Map<String, Object> map = list.get(0);
                if (list.size() != 0 && list1.size() != 0) {
                    Map<String, Object> map1 = list1.get(0);
                    double total_us = (double) map.get("total_user");
                    double new_us = (double) map.get("new_user");
                    double mau_number = (double) map.get("mau_number");
                    double length_time = (double) map.get("length_time");
                    double flow = (double) map.get("flow");
                    double keep_rate = (double) map.get("keep_rate");
                    double total_us1 = (double) map1.get("total_user");
                    double new_us1 = (double) map1.get("new_user");
                    double mau_number1 = (double) map1.get("mau_number");
                    double length_time1 = (double) map1.get("length_time");
                    double flow1 = (double) map1.get("flow");
                    double keep_rate1 = (double) map1.get("keep_rate");
                    if (total_us >= total_us1) {
                        map.put("total_usState", 1);
                    } else {
                        map.put("total_usState", 0);
                    }
                    if (new_us >= new_us1) {
                        map.put("new_usState", 1);
                    } else {
                        map.put("new_usState", 0);
                    }
                    if (mau_number >= mau_number1) {
                        map.put("mau_numberState", 1);
                    } else {
                        map.put("mau_numberState", 0);
                    }
                    if (length_time >= length_time1) {
                        map.put("length_timeState", 1);
                    } else {
                        map.put("length_timeState", 0);
                    }
                    if (flow >= flow1) {
                        map.put("flowState", 1);
                    } else {
                        map.put("flowState", 0);
                    }
                    if (keep_rate >= keep_rate1) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    /**
     *  查询累计用户数
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Override
    public List<Map<String, Object>> getCumulativeList(int app, String month1, String month2,String status) {

        if(status.equals("month")){
            if (month1.equals("null")) {

                try {
                    String defaultTime = DefaultTime.getDefaultTimes(Constants.YEAR, 1, month2);
                    return appMarketGeneralDao.getCumulativeListByMonth(app,defaultTime,month2);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }else {
                return appMarketGeneralDao.getCumulativeListByMonth(app,month1,month2);
            }

        }else if(status.equals("week")){
            return appMarketGeneralDao.getCumulativeListByWeek(app,month1,month2);
        }else {
            return appMarketGeneralDao.getCumulativeListByDate(app,month1,month2);
        }
    }
}
