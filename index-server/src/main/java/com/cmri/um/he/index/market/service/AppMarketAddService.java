package com.cmri.um.he.index.market.service;

import java.util.List;
import java.util.Map;
/**
 * 默认展示时获取查询时间通用方法
 * @author shihao
 * Created on 2018/8/1
 */
public interface AppMarketAddService {
    /**
     * 应用新增页面本月数据展示
     * @param category
     * @param app
     * @param month
     * @return
     */
    List<Map<String, Object>> getAppNumber(Integer category,Integer app,String month);

    /**
     * 应用新增页面新增活跃用户展示
     * @param app
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    List<Map<String, Object>> getAppUser(Integer app,String startTime,String endTime,String status);

    /**
     * 应用新增页面新增次月留存率展示
     * @param app
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    List<Map<String, Object>> getAddRate(Integer app,String startTime,String endTime,String status);

    /**
     * 应用新增页面用户新增数据统计表展示
     * @param app
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    List<Map<String, Object>> getCountNumber(Integer app,String startTime,String endTime,String status);
}
