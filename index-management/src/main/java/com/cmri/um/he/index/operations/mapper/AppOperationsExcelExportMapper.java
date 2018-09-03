package com.cmri.um.he.index.operations.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author lch
 * Created on 2018/08/31 16:51
 */
@Mapper
public interface AppOperationsExcelExportMapper {
    @Select("SELECT app.name app,category.name category,acq.content,acq.channel,acq.tariff,acq.service,acq.market,acq.experience,acq.`month` FROM app_calculation_operations acq JOIN app_info app ON acq.app=app.id JOIN app_category category ON acq.category=category.id ORDER BY `month`,category,app\n")
    List<Map<String,Object>> getAllCalculationQuality();
}
