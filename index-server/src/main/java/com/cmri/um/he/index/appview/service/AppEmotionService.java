package com.cmri.um.he.index.appview.service;


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
     * @param category 应用类别
     * @return 查询结果
     */
    List<Map<String,Object>> find(Integer category);
}
