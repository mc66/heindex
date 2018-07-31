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
    @Select("SELECT am.`length_time` as lengthTime,am.`flow` as flow,am.`month` FROM app_market am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month`")
    List<Map<String, Object>> getLengthTimeBymonth(@Param("app") Integer app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     * 查询人均时长和人均流量(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`length_time` as lengthTime,am.`flow` as flow,am.`month` FROM app_market_week am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month`")
    List<Map<String, Object>> getLengthTimeByWeek(@Param("app") Integer app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     * 查询人均时长和人均流量(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`length_time` as lengthTime,am.`flow` as flow,am.`month` FROM app_market_date am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month`")
    List<Map<String, Object>> getLengthTimeBydate(@Param("app") Integer app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     * 查询应用概括统计(根据月份区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`total_user` AS totalUser,am.`new_user` AS newUser,am.`mau_number` AS mauNumber,am.`keep_rate` AS keepRate,am.`length_time` AS lengthTime,am.`flow` AS flow,am.`month` FROM app_market am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month`\n")
    List<Map<String,Object>> quaryGeneralStatisticByMonth(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询应用概括统计(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`total_user` AS totalUser,am.`new_user` AS newUser,am.`mau_number` AS mauNumber,am.`keep_rate` AS keepRate,am.`length_time` AS lengthTime,am.`flow` AS flow,am.`month` FROM app_market_week am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month`\n")
    List<Map<String,Object>> quaryGeneralStatisticByWeek(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询应用概括统计(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`total_user` AS totalUser,am.`new_user` AS newUser,am.`mau_number` AS mauNumber,am.`keep_rate` AS keepRate,am.`length_time` AS lengthTime,am.`flow` AS flow,am.`month` FROM app_market_date am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month`\n")
    List<Map<String,Object>> quaryGeneralStatisticByDate(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询次月存留率(根据月区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("select category,app, month ,mau_number ,keep_rate  from app_market \n" +
            "where app=#{app} and month BETWEEN #{month1} and #{month2} ORDER BY month desc\n")
    List<Map<String, Object>> getRateByMonth(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询该app的最新月份数据
     * @param app
     * @return
     */
    @Select("select month from app_market where app=#{app} order by month desc limit 1")
    String getNewMonth(@Param("app")Integer app);

    /**
     * 查询次月存留率(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("select category,app, week ,weekly_active ,active_next_week_retention_rate  from app_market_week \n" +
            "where app=#{app} and week BETWEEN #{month1} and #{month2} ORDER BY week desc\n")
    List<Map<String, Object>> getRateByWeek(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);
    /**
     * 查询该app的最新周数据
     * @param app
     * @return
     */
    @Select("select week from app_market_week where app=#{app} order by week desc limit 1")
    String getNewWeek(@Param("app")Integer app);

    /**
     * 查询次月存留率(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("select category,app, date ,dately_active ,active_next_date_retention_rate  from app_market_date \n" +
            "where app=#{app} and date BETWEEN #{month1} and #{month2} ORDER BY date desc\n")
    List<Map<String, Object>> getRateByDate(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);
    /**
     * 查询该app的最新日数据
     * @param app
     * @return
     */
    @Select("select date from app_market_date where app=#{app} order by date desc limit 1")
    String getNewDate(@Param("app")Integer app);

    /**
     * 查存月活跃用户(根据月份区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT m.app , m.new_user ,m.mau_number,m.month\n" +
            "from app_market m\n" +
            "where m.app=#{app} and m.month BETWEEN #{month1} and #{month2} ORDER BY m.month")
    List<Map<String, Object>> getUserNumberByMonth(@Param("app") Integer app,@Param("month1") String  month1,@Param("month2") String  month2 );
    /**
     * 查存月活跃用户(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT m.app , m.new_user ,m.mau_number,m.month\n" +
            "from app_market_week m\n" +
            "where m.app=#{app} and m.month BETWEEN #{month1} and #{month2} ORDER BY m.month")
    List<Map<String, Object>> getUserNumberByWeek(@Param("app") Integer app,@Param("month1") String  month1,@Param("month2") String  month2 );

    /**
     * 查存月活跃用户(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT m.app , m.new_user ,m.mau_number,m.month\n" +
            "from app_market_date m\n" +
            "where m.app=#{app} and m.month BETWEEN #{month1} and #{month2} ORDER BY m.month")
    List<Map<String, Object>> getUserNumberBydate(@Param("app") Integer app,@Param("month1") String  month1,@Param("month2") String  month2 );

    /**
     *  当前月数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    @Select(" SELECT am.total_user,am.new_user,am.mau_number,am.length_time,am.flow,am.keep_rate FROM app_market am WHERE am.app=#{app} and am.category=#{category} and am.`month`=#{month} ")
    List<Map<String,Object>> getMarketMonth(@Param("category") int category,@Param("app") int app,@Param("month") String month);

    /**
     *  上一月数据
     * @param category
     * @param app
     * @param month2
     * @return
     */
    @Select(" SELECT am.total_user,am.new_user,am.mau_number,am.length_time,am.flow,am.keep_rate FROM app_market am WHERE am.app=#{app} and am.category=#{category} and am.`month`=#{month2} ")
    List<Map<String,Object>> getMarketMonth1(@Param("category") int category,@Param("app") int app,@Param("month2") String month2);

    /**
     *   查询month1到month2的数据(根据月份区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select(" SELECT am.total_user,am.`month` FROM app_market am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> getCumulativeListByMonth(@Param("app") int app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     *   查询month1到month2的数据(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select(" SELECT am.total_user,am.`month` FROM app_market_week am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> getCumulativeListByWeek(@Param("app") int app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     *   查询month1到month2的数据(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select(" SELECT am.total_user,am.`month` FROM app_market_date am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> getCumulativeListByDate(@Param("app") int app,@Param("month1") String month1,@Param("month2") String month2);
}
