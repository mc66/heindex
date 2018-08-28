package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 市场指数的关键指标数据
 * @author shihao/machao
 * Created on 2018/6/13
 */
@Mapper
public interface AppMarketMapper {

    /**
     * 根据应用类别id以及月份查询市场指标数据
     * @param category
     * @param month
     * @return
     */
    @Select("SELECT ai.`name`,am.`monthly_active`AS mau_number,am.`active_next_month_retention_rate` AS keep_rate,am.`monthly_duration_per_person` AS length_time,am.`monthly_dataflow_per_person` AS flow,am.`market_index_score` AS market ,ai.`flag`\n" +
            "FROM app_market_month am JOIN app_info ai ON am.`app`=ai.`id`\n" +
            "WHERE am.`category`=#{category} AND am.`month`=#{month} ")
    public List<Map<String,Object>> getAppMarketList(@Param("category") int category, @Param("month") String month);

    /**
     * 查询市场指数的关键指标数据(默认月份时间/根据月份区间)
     * @param category
     * @param month1
     * @return
     */
    @Select("SELECT m.id , a.`name` ,m.`monthly_active`AS mau_number , \n" +
            "m.`active_next_month_retention_rate` AS keep_rate ,m.`monthly_duration_per_person` AS length_time ,m.`monthly_dataflow_per_person` AS flow, m.month \n" +
            "from app_market_month m ,app_info a where m.app=a.id AND  m.category=#{category} AND m.month BETWEEN #{month1} AND #{month2} ORDER BY m.month")
    List<Map<String, Object>> getMarketByMonth(@Param("category") int category, @Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询市场指数的关键指标数据(根据周份区间)
     * @param category
     * @param month1
     * @return
     */
    @Select("SELECT m.id , a.`name`,m.weekly_active AS mau_number, \n" +
            "m.active_next_week_retention_rate AS keep_rate ,m.weekly_duration_per_person AS length_time  ,m.weekly_dataflow_per_person AS flow, m.week \n" +
            "from app_market_week m ,app_info a where m.app=a.id AND  m.category=#{category} AND m.week BETWEEN #{month1} AND #{month2} ORDER BY m.week")
    List<Map<String, Object>> getMarketByWeek(@Param("category") int category, @Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询市场指数的关键指标数据(根据日份区间)
     * @param category
     * @param month1
     * @return
     */
    @Select("SELECT m.id , a.`name`,m.dately_active AS mau_number, \n" +
            "m.active_next_date_retention_rate AS keep_rate ,m.dately_duration_per_person AS length_time ,m.dately_dataflow_per_person AS flow , m.date \n" +
            "from app_market_date m ,app_info a where m.app=a.id AND  m.category=#{category} AND m.date BETWEEN #{month1} AND #{month2} ORDER BY m.date")
    List<Map<String, Object>> getMarketByDate(@Param("category") int category, @Param("month1")String month1,@Param("month2")String month2);
}
