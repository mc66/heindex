package com.cmri.um.he.index.quality.service;

import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;

import java.util.List;

/**
 * 为处理为标准值查询的服务类
 *
 * @author yinjunjun
 * Created on 2018/6/25
 */
public interface StandardService {

    /**
     * 查询未处理为标准值的数据
     * @param month 指定月份
     * @param category 指定类别
     * @return 查询结果
     */
    List<AppCalculationQualityEntity> unprocessedStandard(Integer category,String month);

    /**
     *
     * 计算值转标准值
     * @param appQualityEntities 批量添加的数据
     * */
    void standard(List<AppCalculationQualityEntity> appQualityEntities);

}
