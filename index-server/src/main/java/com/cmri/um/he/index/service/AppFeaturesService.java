package com.cmri.um.he.index.service;

import com.cmri.spring.common.data.PagingData;
import com.cmri.spring.common.data.ResponseMessage;

import java.util.Map;
/**
 * 应用的关键点查询的服务类
 *
 * @author Limin
 * Created on 2018/6/13
 */
public interface AppFeaturesService {



    /**
     * 查询指定月份、应用名的应用的关键点数据
     *
     * @param month    月份，格式示例'201712'
     * @param app      应用名
     * @param page     所要查询的分页，从1开始
     * @param step     每页的记录容量
     * @return 分页查询结果
     */
    PagingData<Map<String, Object>> find(String month, int app, int page, int step);



    /* Map<String,Object> selectAppFeaturesList(String month, Integer app, Integer page, Integer rows);*/
}
