package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserActiveMapper {
    @Select(" SELECT am.total_user,am.new_user,am.mau_number,am.length_time,am.flow,am.keep_rate FROM app_market am WHERE am.app=#{app} and am.category=#{category} and am.`month`=#{month} ")
    List<Map<String,Object>> getActiveMonthData(@Param("category") int category, @Param("app") int app, @Param("month") String month);
    @Select(" SELECT am.month_active_rate,am.mau_number,am.month FROM app_market am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryActivityMonthList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT amw.weekly_active as mau_number,amw.`week` as month,amw.month_active_rate as month_active_rate FROM app_market_week amw WHERE amw.app=#{app} and amw.`week` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryActivityWeekList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT amd.date as month,amd.dately_active as mau_number,amd.date_active_rate as month_active_rate FROM app_market_date amd WHERE amd.app=#{app} and amd.date BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryActivityDayList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT am.month,am.monthly_active_day_per_person,am.monthly_low_freq_user_rate FROM app_market am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryNumberDayMonthList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT amw.`week` as month,amw.weekly_active_day_per_person as monthly_active_day_per_person,amw.weekly_low_freq_user_rate as monthly_low_freq_user_rate FROM app_market_week amw WHERE amw.app=#{app} and amw.`week` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryNumberDayWeekList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT amd.date as month,amd.dately_low_freq_user_rate as monthly_low_freq_user_rate,amd.dately_active_day_per_person as monthly_active_day_per_person FROM app_market_date amd WHERE amd.app=#{app} and amd.date BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryNumberDayDayList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT am.month,am.monthly_duration_per_person,am.monthly_dataflow_per_person FROM app_market am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryBehaviorMonthList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT amw.`week` as month,amw.weekly_duration_per_person as monthly_duration_per_person,amw.weekly_dataflow_per_person as monthly_dataflow_per_person FROM app_market_week amw WHERE amw.app=#{app} and amw.`week` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryBehaviorWeekList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT amd.date as month,amd.dately_duration_per_person as monthly_duration_per_person,amd.dately_dataflow_per_person as monthly_dataflow_per_person FROM app_market_date amd WHERE amd.app=#{app} and amd.date BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryBehaviorDayList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT am.`month`,am.mau_number,am.month_active_rate,am.monthly_active_day_per_person,am.monthly_dataflow_per_person,am.monthly_duration_per_person,am.monthly_low_freq_user_rate FROM app_market am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryStatisticalMonthList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT amw.`week` as month,amw.weekly_duration_per_person as monthly_duration_per_person,amw.weekly_dataflow_per_person as monthly_dataflow_per_person,amw.new_user as mau_number,amw.weekly_active_day_per_person as monthly_active_day_per_person,amw.weekly_low_freq_user_rate as monthly_low_freq_user_rate,amw.weekly_active as month_active_rate FROM app_market_week amw WHERE amw.app=#{app} and amw.`week` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryStatisticalWeekList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
    @Select(" SELECT amd.date as month,amd.dately_duration_per_person as monthly_duration_per_person,amd.dately_dataflow_per_person as monthly_dataflow_per_person,amd.new_user as mau_number,amd.dately_active_day_per_person as monthly_active_day_per_person, amd.dately_low_freq_user_rate as monthly_low_freq_user_rate,amd.dately_active as month_active_rate FROM app_market_date amd  WHERE amd.app=#{app} and amd.date BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> queryStatisticalDayList(@Param("month1") String month1, @Param("app")int app,@Param("month2") String month2);
}
