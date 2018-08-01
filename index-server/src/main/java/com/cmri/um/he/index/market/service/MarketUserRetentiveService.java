package com.cmri.um.he.index.market.service;

import java.util.List;
import java.util.Map;

/**
 * 市场指数的用户留存页面
 * @author machao
 * Created on 2018/7/31
 */
public interface MarketUserRetentiveService {

    /**
     * 查询用户留存率
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    Map<String , Object> quaryUserRetentiveRate(String status, Integer app, String startTime, String endTime);

    /**
     * 查询月用户留存市场指数
     * @param app
     * @param month
     * @return
     */
    Map<String, Object> quaryUserRetentiveExponent(Integer app, String month);

    /**
     * 查询用户留存数
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String , Object>> quaryUserRetentiveNum(String status, Integer app, String startTime, String endTime);

    /**
     * 查询用户留存情况统计表
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String , Object>> quaryUserRetentiveTable(String status, Integer app, String startTime, String endTime);
}
