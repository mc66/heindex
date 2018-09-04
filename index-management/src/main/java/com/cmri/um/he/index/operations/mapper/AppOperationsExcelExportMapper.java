package com.cmri.um.he.index.operations.mapper;

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
public interface AppOperationsExcelExportMapper {
    @Select("SELECT app.name app,category.name category,acq.content,acq.channel,acq.tariff,acq.service,acq.market,acq.experience,acq.`month` FROM app_calculation_operations acq JOIN app_info app ON acq.app=app.id JOIN app_category category ON acq.category=category.id ORDER BY `month`,category,app\n")
    List<Map<String,Object>> getAllCalculationQuality();

    /**
     * 查询当前测量时间所有app
     * @param month
     * @return
     */
    @Select("SELECT DISTINCT aoc.`month`,aoc.app,ai.name from app_original_content aoc LEFT JOIN app_info ai on aoc.app = ai.id WHERE aoc.`month`=#{month}")
    List<Map<String,Object>> quaryAll(@Param("month") String month);

    /**
     * 查询当前app三个类别的样本数
     * @param app
     * @param month
     * @return
     */
    @Select("SELECT COUNT(IF(aoc.content_id=1,TRUE,NULL))as coverSum,\n" +
            "COUNT(IF(aoc.content_id=2,TRUE,NULL))as updateSum,\n" +
            "COUNT(IF(aoc.content_id=3,TRUE,NULL))as recommendSum\n" +
            "from app_original_content aoc where aoc.app=#{app} and aoc.`month`=#{month}")
    Map<String,Long> quarySampleSum(@Param("app")Integer app,@Param("month")String month);


    /**
     * 查询当前app三个类别测量值记为1的数量
     * @param app
     * @param month
     * @return
     */
    @Select("SELECT COUNT(IF(aoc.content_id=1,TRUE,NULL))as coverNum,\n" +
            "COUNT(IF(aoc.content_id=2,TRUE,NULL))as updateNum,\n" +
            "COUNT(IF(aoc.content_id=3,TRUE,NULL))as recommendNum\n" +
            "from app_original_content aoc where aoc.app=#{app} and aoc.`month`=#{month} and measured_value =1")
    Map<String,Long> quarySampleNum(@Param("app")Integer app,@Param("month")String month);
}
