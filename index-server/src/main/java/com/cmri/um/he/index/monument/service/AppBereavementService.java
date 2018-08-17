package com.cmri.um.he.index.monument.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 情感指数变化趋势的服务类
 *
 * @author yinjunjun
 * Created on 2018/8/10
 */
public interface AppBereavementService {

    /**
     * 查询指定月份、指定类别的应用的情感指数变化趋势
     *
     * @param category 应用类别
     * @param startTime 开始月份
     * @param endTime 结束月份
     * @return 查询结果
     */
    List<Map<String,Object>> findBereavement(Integer category,String startTime,String endTime);

    /**
     * 查询指定月份、指定类别的应用的情感指数
     *
     * @param category 应用类别
     * @param endTime 开始月份
     * @return 查询结果
     */
    List<Map<String,Object>> findMoonEmotion(Integer category,String endTime);

    /**
     * 查询评论热词  词频统计
     * @param app 指定app
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 结果集
     * */
    List<Map<String,Object>> frequencyCount(Integer app,String startTime, String endTime);


    /**
     * 查询指定类下的所有app
     * @param category 指定类别
     * @return 结果集
     * */
    List<Map<String,Object>> findCategory(Integer category);

    /**
     *查询月份区间的评论数量统计
     * @param category 类别id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *
     * */
    List<Map<String, Object>> findParameter(Integer category,String startTime, String endTime);


}
