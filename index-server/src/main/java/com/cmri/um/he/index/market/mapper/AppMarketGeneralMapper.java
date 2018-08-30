package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间通用方法
 * @author shihao
 * Created on 2018/7/30
 */
@Mapper
public interface AppMarketGeneralMapper {

    /**
     * 查询人均时长和人均流量(根据月区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT FORMAT(am.`monthly_duration_per_person`,2) as lengthTime,FORMAT(am.`monthly_dataflow_per_person`,2) as flow,am.`month` FROM app_market_month am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month`")
    List<Map<String, Object>> getLengthTimeBymonth(@Param("app") Integer app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     * 查询人均时长和人均流量(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`weekly_duration_per_person` as lengthTime,am.`weekly_dataflow_per_person` as flow,am.`week`AS month FROM app_market_week am WHERE app=#{app} AND am.`week` BETWEEN #{month1} AND #{month2} ORDER BY am.`week`")
    List<Map<String, Object>> getLengthTimeByWeek(@Param("app") Integer app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     * 查询人均时长和人均流量(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`dately_duration_per_person` as lengthTime,am.`dately_dataflow_per_person` as flow,am.`date`AS month FROM app_market_date am WHERE app=#{app} AND am.`date` BETWEEN #{month1} AND #{month2} ORDER BY am.`date`")
    List<Map<String, Object>> getLengthTimeBydate(@Param("app") Integer app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     * 查询应用概括统计(根据月份区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT FORMAT(am.`total_active_num`,2) AS totalUser,FORMAT(am.`new_user`,2) AS newUser,FORMAT(am.`monthly_active`,2) AS mauNumber,FORMAT(am.`active_next_month_retention_rate`,2) AS keepRate,FORMAT(am.`monthly_duration_per_person`,2) AS lengthTime,FORMAT(am.`monthly_dataflow_per_person`,2) AS flow,am.`month` FROM app_market_month am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month` desc\n")
    List<Map<String,Object>> quaryGeneralStatisticByMonth(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询应用概括统计(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`total_active_num` AS totalUser,am.`new_user` AS newUser,am.`weekly_active` AS mauNumber,am.`active_next_week_retention_rate` AS keepRate,am.`weekly_duration_per_person` AS lengthTime,am.`weekly_dataflow_per_person` AS flow,am.`week` AS month FROM app_market_week am WHERE app=#{app} AND am.`week` BETWEEN #{month1} AND #{month2} ORDER BY am.`week` desc\n")
    List<Map<String,Object>> quaryGeneralStatisticByWeek(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询应用概括统计(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`total_active_num` AS totalUser,am.`new_user` AS newUser,am.`dately_active` AS mauNumber,am.`active_next_date_retention_rate` AS keepRate,am.`dately_duration_per_person` AS lengthTime,am.`dately_dataflow_per_person` AS flow,am.`date`AS month FROM app_market_date am WHERE app=#{app} AND am.`date` BETWEEN #{month1} AND #{month2} ORDER BY am.`date`desc\n")
    List<Map<String,Object>> quaryGeneralStatisticByDate(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询次月存留率(根据月区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("select category,app, month ,FORMAT(monthly_active,2) AS mau_number ,FORMAT(active_next_month_retention_rate,2) AS keep_rate  from app_market_month \n" +
            "where app=#{app} and month BETWEEN #{month1} and #{month2} ORDER BY month desc\n")
    List<Map<String, Object>> getRateByMonth(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询次月存留率(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("select category,app, week ,weekly_active ,active_next_week_retention_rate  from app_market_week \n" +
            "where app=#{app} and week BETWEEN #{month1} and #{month2} ORDER BY week desc \n")
    List<Map<String, Object>> getRateByWeek(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询次月存留率(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("select category,app, date ,dately_active ,active_next_date_retention_rate  from app_market_date \n" +
            "where app=#{app} and date BETWEEN #{month1} and #{month2} ORDER BY date desc \n")
    List<Map<String, Object>> getRateByDate(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查存月活跃用户(根据月份区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT m.app , FORMAT(m.new_user,2) AS new_user,FORMAT(m.monthly_active,2) AS mau_number,m.month \n" +
            "from app_market_month m\n" +
            "where m.app=#{app} and m.month BETWEEN #{month1} and #{month2} ORDER BY m.month")
    List<Map<String, Object>> getUserNumberByMonth(@Param("app") Integer app,@Param("month1") String  month1,@Param("month2") String  month2 );
    /**
     * 查存月活跃用户(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT m.app , m.new_user ,m.weekly_active AS mau_number,m.week AS month\n" +
            "from app_market_week m\n" +
            "where m.app=#{app} and m.week BETWEEN #{month1} and #{month2} ORDER BY m.week")
    List<Map<String, Object>> getUserNumberByWeek(@Param("app") Integer app,@Param("month1") String  month1,@Param("month2") String  month2 );

    /**
     * 查存月活跃用户(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT m.app , m.new_user ,m.dately_active AS mau_number,m.date AS month\n" +
            "from app_market_date m\n" +
            "where m.app=#{app} and m.date BETWEEN #{month1} and #{month2} ORDER BY m.date")
    List<Map<String, Object>> getUserNumberBydate(@Param("app") Integer app,@Param("month1") String  month1,@Param("month2") String  month2 );

    /**
     *  当前月数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    @Select(" SELECT FORMAT(am.total_active_num,2) AS total_user,FORMAT(am.new_user,2) AS new_user,FORMAT(am.monthly_active,2) AS mau_number,FORMAT(am.monthly_duration_per_person,2) AS length_time,FORMAT(am.monthly_dataflow_per_person,2) AS flow,FORMAT(am.active_next_month_retention_rate,2) AS keep_rate FROM app_market_month am WHERE am.app=#{app} and am.category=#{category} and am.`month`=#{month} ")
    List<Map<String,Object>> getMarketMonth(@Param("category") int category,@Param("app") int app,@Param("month") String month);

    /**
     *  上一月数据
     * @param category
     * @param app
     * @param month2
     * @return
     */
    @Select(" SELECT FORMAT(am.total_active_num,2) AS total_user,FORMAT(am.new_user,am.monthly_active,2) AS mau_number,FORMAT(am.monthly_duration_per_person,2) AS length_time,FORMAT(am.monthly_dataflow_per_person,2) AS flow,FORMAT(am.active_next_month_retention_rate,2) AS keep_rate FROM app_market_month am WHERE am.app=#{app} and am.category=#{category} and am.`month`=#{month2} ")
    List<Map<String,Object>> getMarketMonth1(@Param("category") int category,@Param("app") int app,@Param("month2") String month2);

    /**
     *   查询month1到month2的数据(根据月份区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select(" SELECT FORMAT(am.total_active_num,2) AS total_user,am.`month` FROM app_market_month am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> getCumulativeListByMonth(@Param("app") int app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     *   查询month1到month2的数据(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select(" SELECT am.total_active_num AS total_user,am.`week` AS month FROM app_market_week am WHERE am.app=#{app} and am.`week` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> getCumulativeListByWeek(@Param("app") int app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     *   查询month1到month2的数据(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select(" SELECT am.total_active_num AS total_user ,am.`date`AS month FROM app_market_date am WHERE am.app=#{app} and am.`date` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> getCumulativeListByDate(@Param("app") int app,@Param("month1") String month1,@Param("month2") String month2);
}
