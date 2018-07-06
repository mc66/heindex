package com.cmri.um.he.index.service;

import com.cmri.spring.common.data.PagingData;

import java.util.List;
import java.util.Map;
/**
* 查询某类应用品质指数
* @author shihao
* Created on 2018/6/13
*/
public interface AppQualityService {
    /**
     * 查询指定期数、指定类别的所有应用的运营指数
     *
     * @param month    月份，格式示例'201706'
     * @param category 应用类别
     * @param page     所要查询的分页，从1开始
     * @param step     每页的记录容量
     * @return 分页查询结果
     */
    PagingData<Map<String, Object>> find(String month,String category, int page, int step);

    /**
     * 查询单个app及月份的数据
     * @param id
     * @param month
     * @return
     */
    List getQualityData(int id, String month);


    /**
     *   查询功能指标
     * @param app
     * @param month
     * @return
     */
    List queryQualityFeatures(Integer app, String month);

    /**
     *    查询界面指标
     * @param app
     * @param month
     * @return
     */
    List queryQualityViews(Integer app, String month);

    /**
     *    查询时延指标
     * @param app
     * @param month
     * @return
     */
    List queryQualityDelay(Integer app, String month);

    /**
     *    查询功耗指标
     * @param app
     * @param month
     * @return
     */
    List queryQualityConsume(Integer app, String month);

    /**
     *    查询使用体验
     * @param app
     * @param month
     * @return
     */
    List queryQualityExperience(Integer app, String month);

    /**
     * 查询app的logo
     * @param appId
     * @return
     */
    List<Map<String,Object>> getAppLogo(int appId);
}
