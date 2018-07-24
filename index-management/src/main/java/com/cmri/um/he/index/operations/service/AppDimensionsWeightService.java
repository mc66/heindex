package com.cmri.um.he.index.operations.service;
import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.operations.entity.AppOriginalOperationsEntity;

import java.util.List;
import java.util.Map;

/**
 * 维度权重管理
 * @author machao
 * Created on 2018/7/17
 */
public interface AppDimensionsWeightService {

    /**
     * 查询所有维度权重
     * @param page  所要查询的分页，从1开始
     * @param step  每页的记录容量
     * @return 分页查询结果
     */
    PagingData<Map<String, Object>> getAllDimensions(int page, int step);

    /**
     * 根据类别查询当前类的维度权重
     * @param aspect
     * @param page
     * @param step
     * @return
     */
    PagingData<Map<String, Object>> getAllDimensionsByAspect(int aspect, int page, int step);

    /**
     * 修改维度权重
     * @param id
     * @param weight
     * @return
     */
    void updateWeight(int id, double weight);
}
