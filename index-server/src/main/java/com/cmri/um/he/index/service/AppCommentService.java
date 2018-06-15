package com.cmri.um.he.index.service;

import com.cmri.spring.common.data.PagingData;

import java.util.Map;

/**
 * 应用评论查询的服务类
 * @author lch
 * Created on 2018/06/13 14:37
 */
public interface AppCommentService {

    /**
     * 查询指定月份、指定类别的应用的市场表现
     *
     * @param month    月份，格式示例'201712'
     * @param category 应用类别
     * @param page     所要查询的分页，从1开始
     * @param step     每页的记录容量
     * @return 分页查询结果
     */
    PagingData<Map<String, Object>> find(String month, int category, int page, int step);
}
