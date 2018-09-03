package com.cmri.um.he.index.quality.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author lch
 * Created on 2018/08/31 16:51
 */
@Mapper
public interface AppQualityExcelExportMapper {
    @Select("SELECT app.name app,category.name category,acq.delay,acq.consume,acq.features,acq.`view`,acq.experience,acq.`month` FROM app_calculation_quality acq JOIN app_info app ON acq.app=app.id JOIN app_category category ON acq.category=category.id ORDER BY `month`,category,app")
    List<Map<String,Object>> getAllCalculationQuality();
}
