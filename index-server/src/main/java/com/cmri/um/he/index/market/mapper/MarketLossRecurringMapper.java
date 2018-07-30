package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 市场指数的流失与回流页面
 * @author machao
 * Created on 2018/7/27
 */
@Mapper
public interface MarketLossRecurringMapper {
    /**
     * 查询周的用户流失情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amw.`week`,amw.`next_week_loss_num`AS lossNum,amw.`active_next_week_loss_rate`AS lossRate FROM app_market_week amw WHERE amw.`app`=#{app} AND amw.`week` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> weekUserActive(@Param("app") Integer app,@Param("startTime")String startTime,@Param("endTime") String endTime);

    /**
     * 查询月的用户流失情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT am.`month`,am.`next_month_loss_num`AS lossNum,am.`active_next_month_loss_rate`AS lossRate FROM app_market am WHERE am.`app`= #{app} AND am.`month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> monthUserActive(@Param("app") Integer app,@Param("startTime")String startTime,@Param("endTime") String endTime);

    /**
     * 查询日的用户流失情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amd.`date`,amd.`next_date_loss_num`AS lossNum,amd.`active_next_date_loss_rate`AS lossRate FROM app_market_date amd WHERE amd.`app`=#{app} AND amd.`date` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> dayUserActive(@Param("app") Integer app,@Param("startTime")String startTime,@Param("endTime") String endTime);

    /**
     * 查询月流失回流指数
     * @param app
     * @param month
     * @return
     */
    @Select("SELECT am.`next_month_loss_num`AS lossNum,am.`active_next_month_loss_rate`AS lossRate,am.`next_month_recurring_num`AS recurringNum,am.`loss_next_month_recurring_rate`AS recurringRate FROM app_market am WHERE am.`app`=#{app} AND am.`month`=#{month}")
    Map<String, Object>  quaryLossRecurringExponent(@Param("app")Integer app, @Param("month")String month);

    /**
     * 查询周的用户回流情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amw.`week`,amw.`next_week_recurring_num`AS recurringNum,amw.`loss_next_week_recurring_rate`AS recurringRate FROM app_market_week amw WHERE amw.`app`=#{app} AND amw.`week` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> weekUserRecurring(@Param("app") Integer app,@Param("startTime")String startTime,@Param("endTime") String endTime);

    /**
     * 查询月的用户回流情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT am.`month`,am.`next_month_recurring_num`AS recurringNum,am.`loss_next_month_recurring_rate`AS recurringRate FROM app_market am WHERE am.`app`= #{app} AND am.`month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> monthUserRecurring(@Param("app") Integer app,@Param("startTime")String startTime,@Param("endTime") String endTime);

    /**
     * 查询日的用户回流情况
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amd.`date`,amd.`next_date_recurring_num`AS recurringNum,amd.`loss_next_date_recurring_rate`AS recurringRate FROM app_market_date amd WHERE amd.`app`=#{app} AND amd.`date` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> dayUserRecurring(@Param("app") Integer app,@Param("startTime")String startTime,@Param("endTime") String endTime);

    /**
     * 查询周的用户情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amw.`week`,amw.`next_week_recurring_num`AS recurringNum,amw.`loss_next_week_recurring_rate`AS recurringRate,amw.`next_week_loss_num`AS lossNum,amw.`active_next_week_loss_rate`AS lossRate FROM app_market_week amw WHERE amw.`app`=#{app} AND amw.`week` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> weekStatisticalTable(@Param("app") Integer app,@Param("startTime")String startTime,@Param("endTime") String endTime);

    /**
     * 查询月的用户情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT am.`month`,am.`next_month_recurring_num`AS recurringNum,am.`loss_next_month_recurring_rate`AS recurringRate,am.`next_month_loss_num`AS lossNum,am.`active_next_month_loss_rate`AS lossRate FROM app_market am WHERE am.`app`= #{app} AND am.`month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> monthStatisticalTable(@Param("app") Integer app,@Param("startTime")String startTime,@Param("endTime") String endTime);

    /**
     * 查询日的用户情况统计表
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT amd.`date`,amd.`next_date_recurring_num`AS recurringNum,amd.`loss_next_date_recurring_rate`AS recurringRate,amd.`next_date_loss_num`AS lossNum,amd.`active_next_date_loss_rate`AS lossRate FROM app_market_date amd WHERE amd.`app`=#{app} AND amd.`date` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String, Object>> dayStatisticalTable(@Param("app") Integer app,@Param("startTime")String startTime,@Param("endTime") String endTime);

}
