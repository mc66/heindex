package com.cmri.um.he.index.quality.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Select("select DISTINCT month from app_original_features")
    List<Map<String,Object>> getMonth();

    @Select("select DISTINCT f.app , i.`name` appName,c.`name` categoryName from app_original_features f LEFT JOIN app_info i ON f.app=i.id  LEFT JOIN app_category c ON c.id=f.category where month=#{month}")
    List<Map<String,Object>>  getAPP(@Param("month") String month);

    @Select("select DISTINCT dimensions  from app_original_features where app=#{app} AND month=#{month}")
    List<Map<String,Object>>  getWeight(@Param("app") int app,@Param("month") String month);

    @Select("select COUNT(*) from app_original_features where app=#{app} AND month=#{month} AND dimensions =#{dimensions} and degree ='高'")
    int getCount1(@Param("app") Integer app,@Param("month") String month,@Param("dimensions") String dimensions);
    @Select("select COUNT(*) from app_original_features where app=#{app} AND month=#{month} AND dimensions =#{dimensions} and degree ='中'")
    int getCount2(@Param("app") Integer app,@Param("month") String month,@Param("dimensions") String dimensions);
    @Select("select COUNT(*) from app_original_features where app=#{app} AND month=#{month} AND dimensions =#{dimensions} and degree ='低'")
    int getCount3(@Param("app") Integer app,@Param("month") String month,@Param("dimensions") String dimensions);
}
