package com.cmri.um.he.index.service;

import com.cmri.um.he.index.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.entity.AppQualityEntity;

import java.util.List;
import java.util.Map;

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
    List<Map<String,Object>> unprocessedStandard(Integer category,String month);

    /**
     *
     * 计算值转标准值
     * @param appQualityEntities 批量添加的数据
     * */
    void standard(List<AppCalculationQualityEntity> appQualityEntities);

}
