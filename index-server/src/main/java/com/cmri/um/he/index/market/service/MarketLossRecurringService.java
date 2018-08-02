package com.cmri.um.he.index.market.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 市场指数的流失与回流页面
 * @author machao
 * Created on 2018/7/27
 */
public interface MarketLossRecurringService {

    /**
     * 查询用户流失情况
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String , Object>> quaryUserLoss(String status,Integer app ,String startTime,String endTime);

    /**
     * 查询月流失回流指数
     * @param app
     * @param month
     * @return
     */
    List<Map<String, Object>> quaryLossRecurringExponent(Integer app ,String month);

    /**
     * 查询用户回流情况
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String , Object>> quaryUserRecurring(String status,Integer app ,String startTime,String endTime);

    /**
     * 查询用户情况统计表
     * @param status
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String , Object>> quaryStatisticalTable(String status,Integer app ,String startTime,String endTime);
}
