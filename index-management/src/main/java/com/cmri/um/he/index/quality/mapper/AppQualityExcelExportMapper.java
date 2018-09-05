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

    @Select("SELECT cat.name category,awq.w3g,awq.w4g,awq.wwlan,awq.whigh,awq.wmiddle,awq.wlow,awq.wdelay,awq.wconsume,awq.wfeatures,awq.wview,awq.wexperience,awq.wcontent,awq.wchannel,awq.wmarket,awq.wexpenses,awq.wservice,awq.wexperience_operation FROM app_weight_quality awq JOIN app_category cat ON awq.category=cat.id")
    List<Map<String,Object>> getAllWeight();
}
