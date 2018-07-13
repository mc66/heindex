package com.cmri.um.he.index.appview.service;


import java.util.List;
import java.util.Map;

/**
 * 某类应用品质、运营查询的服务类
 *
 * @author yinjunjun
 * Created on 2018/6/19
 */
public interface AppSummaryService {
    /**
     * 查询指定月份、指定类别的应用的品质总结
     *
     * @param month    月份，格式示例'201712'
     * @param category 应用类别
     * @return 查询结果
     */
    List<Map<String,Object>> findQuality(String month, Integer category);

    /**
     * 查询指定月份、指定类别的应用的运营总结
     *
     * @param month    月份，格式示例'201712'
     * @param category 应用类别
     * @return 查询结果
     */
    List<Map<String,Object>> findOperation(String month, Integer category);
}
