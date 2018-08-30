package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
/**
 * 默认展示时获取查询时间通用方法
 * @author shihao
 * Created on 2018/8/1
 */
@Mapper
public interface AppMarketAddMapper {

    /**
     * 应用新增页面本月数据展示
     * @param category
     * @param app
     * @param month
     * @return
     */
    @Select("SELECT new_user, new_user_retention_rate AS rate from app_market_month where category=#{category} AND app=#{app} AND month=#{month}")
    List<Map<String, Object>> getAppNumber(@Param("category") Integer category,@Param("app") Integer app,@Param("month") String month);

    /**
     * 应用新增页面新增活跃用户展示(根据月份区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT new_user ,month AS time from app_market_month where app=#{app} and month BETWEEN #{startTime} AND #{endTime} order by month desc")
    List<Map<String, Object>> getAppUserByMonth(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);
    /**
     * 应用新增页面新增活跃用户展示(根据周区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT new_user ,week AS time from app_market_week where app=#{app} and week BETWEEN #{startTime} AND #{endTime} order by week desc")
    List<Map<String, Object>> getAppUserByWeek(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);
    /**
     * 应用新增页面新增活跃用户展示(根据日区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT new_user ,date AS time from app_market_date where app=#{app} and date BETWEEN #{startTime} AND #{endTime} order by date desc")
    List<Map<String, Object>> getAppUserByDate(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 应用新增页面新增次月留存率展示(根据月区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("select category,app, month ,monthly_active AS mau_number ,new_user_retention_rate  from app_market_month \n" +
            "where app=#{app} and month BETWEEN #{startTime} and #{endTime} ORDER BY month desc\n")
    List<Map<String, Object>> getAddRateByMonth(@Param("app")Integer app,@Param("startTime")String startTime,@Param("endTime")String endTime);
    /**
     * 应用新增页面新增次月留存率展示(根据周区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("select category,app, week ,weekly_active ,new_user_retention_rate  from app_market_week \n" +
            "where app=#{app} and week BETWEEN #{startTime} and #{endTime} ORDER BY week desc \n")
    List<Map<String, Object>> getAddRateByWeek(@Param("app")Integer app,@Param("startTime")String startTime,@Param("endTime")String endTime);

    /**
     * 应用新增页面新增次月留存率展示(根据日区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("select category,app, date ,dately_active ,new_user_retention_rate  from app_market_date \n" +
            "where app=#{app} and date BETWEEN #{startTime} and #{endTime} ORDER BY date desc \n")
    List<Map<String, Object>> getAddRateByDate(@Param("app")Integer app,@Param("startTime")String startTime,@Param("endTime")String endTime);

    /**
     * 应用新增页面用户新增数据统计表展示(根据月区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT month AS time ,total_active_num AS total_user,penetration_rate FROM app_market_month \n" +
            "where app=#{app} and month BETWEEN #{startTime} AND #{endTime} order by month desc")
    List<Map<String, Object>> getCountNumberByMonth(@Param("app")Integer app,@Param("startTime")String startTime,@Param("endTime")String endTime);

    /**
     * 应用新增页面用户新增数据统计表展示(根据周区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT week AS time ,total_active_num AS total_user,penetration_rate FROM app_market_week \n" +
            "where app=#{app} and week BETWEEN #{startTime} AND #{endTime} order by week desc")
    List<Map<String, Object>> getCountNumberByWeek(@Param("app")Integer app,@Param("startTime")String startTime,@Param("endTime")String endTime);
    /**
     * 应用新增页面用户新增数据统计表展示(根据日区间)
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT date AS time ,total_active_num AS total_user,penetration_rate FROM app_market_date \n" +
            "where app=#{app} and date BETWEEN #{startTime} AND #{endTime} order by date desc")
    List<Map<String, Object>> getCountNumberByDate(@Param("app")Integer app,@Param("startTime")String startTime,@Param("endTime")String endTime);
}
