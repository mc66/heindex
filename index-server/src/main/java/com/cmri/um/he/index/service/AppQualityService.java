package com.cmri.um.he.index.service;

import com.cmri.spring.common.data.PagingData;

import java.util.Map;
/**
* 查询某类应用品质指数
* @author shihao
* Created on 2018/6/13
*/
public interface AppQualityService {
    /**
     * AppQualityService
     */
    PagingData<Map<String, Object>> find(String month,String category, int page, int step);
}
