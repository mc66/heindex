package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.market.dao.AppMarketAddDao;
import com.cmri.um.he.index.market.service.AppMarketAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * 默认展示时获取查询时间通用方法
 * @author shihao
 * Created on 2018/8/1
 */
@Service
public class AppMarketAddServiceImpl implements AppMarketAddService {

    @Autowired
    private AppMarketAddDao appMarketAddDao;

    /**
     * 应用新增页面本月数据展示
     *
     * @param category
     * @param app
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Object>> getAppNumber(Integer category, Integer app, String month) {

        try {
            String defaultTime = DefaultTime.getDefaultTimes(Constants.MONTH, 1, month);
            List<Map<String, Object>> list1 = appMarketAddDao.getAppNumber(category, app, month);
            List<Map<String, Object>> list2 = appMarketAddDao.getAppNumber(category, app, defaultTime);

            if ((int) list1.get(0).get("new_user") > (int) list2.get(0).get("new_user")) {
                list1.get(0).put("new_userSatte", 1);
            } else {
                list1.get(0).put("new_userSatte", 0);
            }
            if ((double) list1.get(0).get("rate") > (double) list2.get(0).get("rate")) {
                list1.get(0).put("rateSatte", 1);
            } else {
                list1.get(0).put("rateSatte", 0);
            }
            if (list2 == null && list2.size() == 0) {
                list1.get(0).put("new_userSatte", 1);
                list1.get(0).put("rateSatte", 1);
            }
            return list1;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 应用新增页面新增活跃用户展示
     *
     * @param app
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    @Override
    public List<Map<String, Object>> getAppUser(Integer app, String startTime, String endTime, String status) {
        if (status.equals("month")) {
            if (startTime.equals("null")) {

                try {
                    String defaultTime = DefaultTime.getDefaultTimes(Constants.YEAR, 1, endTime);
                    return  appMarketAddDao.getAppUserByMonth(app, defaultTime, endTime);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }else {
                return appMarketAddDao.getAppUserByMonth(app, startTime, endTime);
            }
        } else if (status.equals("week")) {
            return appMarketAddDao.getAppUserByWeek(app, startTime, endTime);
        } else {
            return appMarketAddDao.getAppUserByDate(app, startTime, endTime);
        }
    }

    /**
     * 应用新增页面新增次月留存率展示
     *
     * @param app
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    @Override
    public List<Map<String, Object>> getAddRate(Integer app, String startTime, String endTime, String status) {
        List<Map<String, Object>> lists = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (status.equals("month")) {
            if (startTime.equals("null")) {

                try {
                    String defaultTime = DefaultTime.getDefaultTimes(Constants.YEAR, 1, endTime);
                    List<Map<String, Object>> maplist = appMarketAddDao.getAddRateByMonth(app, defaultTime, endTime);
                    List list = new ArrayList();
                    List listmonth = new ArrayList();
                    List listmau = new ArrayList();
                    for (Map<String, Object> ob : maplist) {
                        Object month = ob.get("month");
                        listmonth.add(month);
                        Object mau_number = ob.get("mau_number");
                        listmau.add(mau_number);
                    }
                    //次月存活率
                    for (int i = 0; i < listmonth.size(); i++) {
                        startTime = (String) listmonth.get(i);
                        endTime = (String) listmonth.get(0);
                        List<Map<String, Object>> list2 = appMarketAddDao.getAddRateByMonth(app, startTime, endTime);
                        Collections.reverse(list2);
                        List list4 = new ArrayList();
                        for (Map<String, Object> obmap : list2) {
                            Object keep_rate = obmap.get("new_user_retention_rate");
                            list4.add(keep_rate);
                        }
                        list.add(list4);
                    }
                    map.put("rate", list);
                    String[] st = {"时间", "活跃用户数(万)", "第2月", "第3月", "第4月", "第5月", "第6月", "第7月", "第8月", "第9月"};
                    map.put("time", listmonth);
                    map.put("user_number", listmau);
                    map.put("title", st);
                    lists.add(map);
                    return lists;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                List list = new ArrayList();
                List<Map<String, Object>> maplist = appMarketAddDao.getAddRateByMonth(app, startTime, endTime);
                List listmonth = new ArrayList();
                List listmau = new ArrayList();
                for (Map<String, Object> ob : maplist) {
                    Object month = ob.get("month");
                    listmonth.add(month);
                    Object mau_number = ob.get("mau_number");
                    listmau.add(mau_number);
                }
                //次月存活率
                for (int i = 0; i < listmonth.size(); i++) {
                    startTime = (String) listmonth.get(i);
                    endTime = (String) listmonth.get(0);
                    List<Map<String, Object>> list2 = appMarketAddDao.getAddRateByMonth(app, startTime, endTime);
                    Collections.reverse(list2);
                    List list4 = new ArrayList();
                    for (Map<String, Object> obmap : list2) {
                        Object keep_rate = obmap.get("new_user_retention_rate");
                        list4.add(keep_rate);
                    }
                    list.add(list4);
                }
                String[] st = {"时间", "活跃用户数(万)", "第2月", "第3月", "第4月", "第5月", "第6月", "第7月", "第8月", "第9月"};
                map.put("time", listmonth);
                map.put("user_number", listmau);
                map.put("title", st);
                map.put("rate", list);
                lists.add(map);
                return lists;
            }
        } else if (status.equals("week")) {
            List list = new ArrayList();
            List<Map<String, Object>> maplist = appMarketAddDao.getAddRateByWeek(app, startTime, endTime);
            List listweek = new ArrayList();
            List listmau = new ArrayList();
            for (Map<String, Object> ob : maplist) {
                Object week = ob.get("week");
                listweek.add(week);
                Object weeky_active = ob.get("weekly_active");
                listmau.add(weeky_active);
            }
            //次周存活率
            for (int i = 0; i < listweek.size(); i++) {
                startTime = (String) listweek.get(i);
                endTime = (String) listweek.get(0);
                List<Map<String, Object>> list2 = appMarketAddDao.getAddRateByWeek(app, startTime, endTime);
                Collections.reverse(list2);
                List list4 = new ArrayList();
                for (Map<String, Object> obmap : list2) {
                    Object keep_rate = obmap.get("new_user_retention_rate");
                    list4.add(keep_rate);
                }
                list.add(list4);
            }

            String[] st = {"时间", "活跃用户数(万)", "第2周", "第3周", "第4周", "第5周", "第6周", "第7周", "第8周", "第9周"};
            map.put("time", listweek);
            map.put("user_number", listmau);
            map.put("title", st);
            map.put("rate", list);
            lists.add(map);
            return lists;

        } else {
            List list = new ArrayList();
            List<Map<String, Object>> maplist = appMarketAddDao.getAddRateByDate(app, startTime, endTime);
            List listdate = new ArrayList();
            List listmau = new ArrayList();
            for (Map<String, Object> ob : maplist) {
                Object date = ob.get("date");
                listdate.add(date);
                Object dately_active = ob.get("dately_active");
                listmau.add(dately_active);
            }
            //次日存活率
            for (int i = 0; i < listdate.size(); i++) {
                startTime = (String) listdate.get(i);
                endTime = (String) listdate.get(0);
                List<Map<String, Object>> list2 = appMarketAddDao.getAddRateByDate(app, startTime, endTime);
                Collections.reverse(list2);
                List list4 = new ArrayList();
                for (Map<String, Object> obmap : list2) {
                    Object keep_rate = obmap.get("new_user_retention_rate");
                    list4.add(keep_rate);
                }
                list.add(list4);
            }
            String[] st = {"时间", "活跃用户数(万)", "第2日", "第3日", "第4日", "第5日", "第6日", "第7日", "第15日", "第30日"};
            map.put("time", listdate);
            map.put("user_number", listmau);
            map.put("title", st);
            map.put("rate", list);
            lists.add(map);
            return lists;
        }
    }

    /**
     * 应用新增页面用户新增数据统计表展示
     * @param app
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    @Override
    public List<Map<String, Object>> getCountNumber(Integer app, String startTime, String endTime, String status) {
        if (status.equals("month")) {
            if (startTime.equals("null")) {

                try {
                    String defaultTime = DefaultTime.getDefaultTimes(Constants.YEAR, 1, endTime);
                    return  appMarketAddDao.getCountNumberByMonth(app, defaultTime, endTime);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }else {
                return  appMarketAddDao.getCountNumberByMonth(app, startTime, endTime);
            }
        } else if (status.equals("week")) {
            return appMarketAddDao.getCountNumberByWeek(app, startTime, endTime);
        } else {
            return appMarketAddDao.getCountNumberByDate(app, startTime, endTime);
        }
    }

}

