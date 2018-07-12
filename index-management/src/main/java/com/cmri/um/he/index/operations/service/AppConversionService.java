package com.cmri.um.he.index.operations.service;

import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 转换标准值的服务类
 *
 * @author yinjunjun
 * Created on 2018/07/10
 */
public interface AppConversionService {

    /**
     * 查询还没有转换的数据
     * @param month 指定查询的月份
     * @param category 指定查询的类别
     * @return 未转换数据集合
     * */
    List<Map<String,Object>> queryUnConversion(Integer category,String month);

    /**
     * 计算值转标准值
     * @param appCalculationOperationsEntities 未转换数据集合
     * */
    void saveAll(List<AppCalculationOperationsEntity> appCalculationOperationsEntities);

    /**
     * 查询所有报告期数
     * @return 查询结果
     */
    List<Map<String,Object>> findMonths();
}
