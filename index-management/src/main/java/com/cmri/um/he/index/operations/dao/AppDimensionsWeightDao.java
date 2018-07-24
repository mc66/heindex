package com.cmri.um.he.index.operations.dao;

import com.cmri.um.he.index.operations.mapper.AppDimensionsWeightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 维度权重管理
 * @author machao
 * Created on 2018/7/17
 */
@Repository
public class AppDimensionsWeightDao extends BaseDao{

    @Autowired
    private AppDimensionsWeightMapper mapper;

    /**
     * 查询指定所有维度数量
     */
    public int count() {
        return mapper.count();
    }

    /**
     * 查询指定类别的维度数量
     */
    public int countOfAspect(int aspect) {
        return mapper.countOfCategory(aspect);
    }

    /**
     * 分页查询所有维度权重
     */
    public List<Map<String, Object>> getAllDimensions(int page, int step) {
        return addNo(mapper.getAllDimensions(offset(page, step), step));
    }

    /**
     * 分页查询指定类别的维度权重
     */
    public List<Map<String, Object>> getAllDimensionsByAspect(int aspect, int page, int step) {
        return addNo(mapper.getAllDimensionsByAspect(aspect, offset(page, step), step));
    }

    /**
     * 为记录添加序号
     */
    private List<Map<String, Object>> addNo(List<Map<String, Object>> items) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).put("no", i);
        }
        return items;
    }

    public void updateWeight(int id,double weight){
        mapper.updateWeight(id, weight);
    }
}
