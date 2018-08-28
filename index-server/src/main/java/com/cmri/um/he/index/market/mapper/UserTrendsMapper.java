package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
/**
 * @author  limin
 */
@Mapper
public interface UserTrendsMapper {

    @Select(" SELECT am.total_active_num as total_user,am.penetration_rate FROM app_market_month am WHERE am.app=#{app} and am.category=#{category} and am.`month`=#{month} ")
    List<Map<String,Object>> queryMonthsDataList(@Param("category") int category,@Param("app") int app,@Param("month") String month);

    @Select(" SELECT am.month,am.penetration_rate FROM app_market_month am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryPenetrationMonthList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);

    @Select(" SELECT amw.`week` as month,amw.penetration_rate FROM app_market_week amw WHERE amw.app=#{app} and amw.`week` BETWEEN #{month1} and #{month2}  ")
    List<Map<String,Object>> queryPenetrationWeekList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);

    @Select(" SELECT amd.date as month,amd.penetration_rate FROM app_market_date amd WHERE amd.app=#{app} and amd.`date` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryPenetrationDayList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);

    @Select(" SELECT am.`month`,am.total_active_num as total_user,am.penetration_rate FROM app_market_month am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryUserStatisticsMonthList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);

    @Select(" SELECT amw.`week` as month,amw.total_active_num as total_user, amw.penetration_rate FROM app_market_week amw WHERE amw.app=#{app} and amw.`week` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryUserStatisticsWeekList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);

    @Select(" SELECT amd.date as month,amd.total_active_num as total_user,amd.penetration_rate FROM app_market_date amd WHERE amd.app=#{app} and amd.`date` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryUserStatisticsDayList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
}
