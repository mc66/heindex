package com.cmri.um.he.index.service;


import java.util.List;
import java.util.Map;

/**
 * 某类应用情感得分查询的服务类
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
public interface AppEmotionService {

    /**
     * 查询指定月份、指定类别的应用的市场表现
     *
     * @param month    月份，格式示例'201712'
     * @param category 应用类别
     * @return 查询结果
     */
    List<Map<String,Object>> find(String month,String category);
}
