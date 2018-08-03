package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 市场指数的用户留存页面
 * @author machao
 * Created on 2018/7/31
 */
@Mapper
public interface MarketUserRetentiveMapper {
    /**
     * 查询周的用户留存数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amw.`week`AS MONTH,amw.`next_week_retention_num`AS retentionNum FROM app_market_week amw WHERE amw.`app`=#{app} AND amw.`week` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> weekUserRetentiveNum(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询月的用户留存数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT am.`month`,am.`next_month_retention_num`AS retentionNum FROM app_market am WHERE am.`app`= #{app} AND am.`month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> monthUserRetentiveNum(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询日的用户留存数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amd.`date`AS month,amd.`next_date_retention_num`AS retentionNum FROM app_market_date amd WHERE amd.`app`=#{app} AND amd.`date` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> dayUserRetentiveNum(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询月用户留存指数
     * @param app
     * @param month
     * @return
     */
    @Select("SELECT am.`next_month_retention_num`AS retentionNum,am.`active_next_month_retention_rate`AS retentionRate FROM app_market am WHERE am.`app`=#{app} AND am.`month`=#{month}")
    Map<String, Object>  quaryUserRetentiveExponent(@Param("app") Integer app, @Param("month") String month);

    /**
     * 查询周的用户留存情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amw.`week`AS month,amw.`next_week_retention_num`AS retentionNum,amw.`active_next_week_retention_rate`AS retentionRate  FROM app_market_week amw WHERE amw.`app`=#{app} AND amw.`week` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> weekUserRetentiveTable(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询月的用户留存情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT am.`month`,am.`next_month_retention_num`AS retentionNum,am.`active_next_month_retention_rate`AS retentionRate FROM app_market am WHERE am.`app`= #{app} AND am.`month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> monthUserRetentiveTable(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询日的用户留存情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amd.`date`AS month,amd.`next_date_retention_num`AS retentionNum,amd.`active_next_date_retention_rate`AS retentionRate FROM app_market_date amd WHERE amd.`app`=#{app} AND amd.`date` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> dayUserRetentiveTable(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime") String endTime);


    /**
     * 查询周的时间范围
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amw.`week` FROM app_market_week amw WHERE amw.`app`=#{app} AND amw.`week` BETWEEN #{startTime} AND #{endTime} ORDER BY amw.`week` DESC")
    List<String> weekMonthList(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime") String endTime);
    /**
     * 查询月的时间范围
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT am.`month` FROM app_market am WHERE am.`app`=#{app} AND am.`month` BETWEEN #{startTime} AND #{endTime} ORDER BY am.`month` DESC")
    List<String> monthMonthList(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime")String endTime);
    /**
     * 查询日的时间范围
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amd.`date` FROM app_market_date amd WHERE amd.`app`=#{app} AND amd.`date` BETWEEN #{startTime} AND #{endTime} ORDER BY amd.`date` DESC")
    List<String> dayMonthList(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime")String endTime);

    /**
     * 查询周的活跃用户数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amw.`weekly_active` FROM app_market_week amw WHERE amw.`app`=#{app} AND amw.`week` BETWEEN #{startTime} AND #{endTime} ORDER BY amw.`week` DESC")
    List<Double> weekUserActiveList(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime")String endTime);
    /**
     * 查询月的活跃用户数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT am.`mau_number` FROM app_market am WHERE am.`app`=#{app} AND am.`month` BETWEEN #{startTime} AND #{endTime} ORDER BY am.`month` DESC")
    List<Double> monthUserActiveList(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime")String endTime);

    /**
     * 查询日的活跃用户数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amd.`dately_active` FROM app_market_date amd WHERE amd.`app`=#{app} AND amd.`date` BETWEEN #{startTime} AND #{endTime} ORDER BY amd.`date` DESC")
    List<Double> dayUserActiveList(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime")String endTime);

    /**
     * 查询周的留存率
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amw.`active_next_week_retention_rate` FROM app_market_week amw WHERE amw.`app`=#{app} AND amw.`week` BETWEEN #{startTime} AND #{endTime} ORDER BY amw.`week`")
    List<Double> weekRetentiveRateList(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime")String endTime);

    /**
     * 查询月的留存率
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT am.`active_next_month_retention_rate` FROM app_market am WHERE am.`app`=#{app} AND am.`month` BETWEEN #{startTime} AND #{endTime} ORDER BY am.`month`")
    List<Double> monthRetentiveRateList(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime")String endTime);

    /**
     * 查询日的留存率
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amd.`active_next_date_retention_rate` FROM app_market_date amd WHERE amd.`app`=#{app} AND amd.`date` BETWEEN #{startTime} AND #{endTime} ORDER BY amd.`date`")
    List<Double> dayRetentiveRateList(@Param("app") Integer app, @Param("startTime") String startTime, @Param("endTime")String endTime);

}
