package com.cmri.um.he.index.monument.service;

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

    List<Map<String,Object>> findNumberCommentsJust(Integer category, String startTime, String endTime);

    List<Map<String,Object>> findAppName(Integer category, String startTime, String endTime);

    List<Map<String,Object>> findNumberCommentsCentre(Integer category, String startTime, String endTime);

    List<Map<String,Object>> findNumberCommentsLoad(Integer category, String startTime, String endTime);
}
