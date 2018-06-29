package com.cmri.um.he.index.service;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.entity.AppQualityEntity;

import java.util.List;
import java.util.Map;


/**
 * 查询未计算体验指数的所有应用，计算应用的品质总分
 *
 * @author machao
 * Created on 2018/6/28
 */
public interface AppQualityService {
    /**
     * 查询所有未计算的应用
     * @return
     */
    public List<Map<String, Object>> quary();

    /**
     * 计算应用的品质得分并粗如数据库
     * @param qualityEntityList
     * @return
     */
    public boolean setQindex(List<Map<String, Object>> qualityEntityList);
}
