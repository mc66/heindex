package com.cmri.um.he.index.service;

import com.cmri.spring.common.data.PagingData;

import java.util.Map;


/**
 * 根据期数查询当前应用类所有应用的运营数据的服务类
 *
 * @author machao
 * Created on 2018/6/13
 */
public interface AppOperationsService {
    /**
     * 查询指定期数、指定类别的所有应用的运营指数
     *
     * @param month    月份，格式示例'201712'
     * @param category 应用类别
     * @param page     所要查询的分页，从1开始
     * @param step     每页的记录容量
     * @return 分页查询结果
     */
    PagingData<Map<String, Object>> getOperationsList(String month, String category, int page, int step);
}
