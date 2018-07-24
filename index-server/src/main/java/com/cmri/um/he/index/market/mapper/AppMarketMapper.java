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
    @Select("SELECT ai.`name`,am.`mau_number`,am.`keep_rate`,am.`length_time`,am.`flow`,am.`market`,ai.`flag`\n" +
            "FROM app_market am JOIN app_info ai ON am.`app`=ai.`id`\n" +
            "WHERE am.`category`=#{category} AND am.`month`=#{month} ")
    public List<Map<String,Object>> getAppMarketList(@Param("category") int category, @Param("month") String month);

    /**
     * 查询市场指数的关键指标数据(默认月份时间)
     * @param category
     * @param month1
     * @return
     */
    @Select("SELECT m.id , a.`name` app_name,m.mau_number mau_number, \n" +
            "m.keep_rate keep_rate,m. length_time  length_time ,m.flow flow, m.month month\n" +
            "from app_market m ,app_info a where m.app=a.id AND  m.category=#{category} AND m.month BETWEEN #{month2} AND #{month1} ORDER BY m.month")
    List<Map<String, Object>> getMarket(@Param("category") int category, @Param("month1")String month1,@Param("month2")String month2);

    /**
     * 查询市场指数的关键指标数据(根据月份区间)
     * @param category
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT m.id , a.`name` app_name,m.mau_number mau_number, \n" +
            "m.keep_rate keep_rate,m. length_time  length_time ,m.flow flow, m.month month\n" +
            "from app_market m ,app_info a where m.app=a.id AND  m.category=#{category} AND m.month BETWEEN #{month1} AND #{month2} ORDER BY m.month" )
    List<Map<String, Object>> getMarketList(@Param("category") int category, @Param("month1")String month1,@Param("month2")String month2);


    @Select("select category,app, month ,mau_number AS '月活跃用户',keep_rate AS '次月存留率' from app_market \n" +
            "where app=66 and category=3 and month BETWEEN 201801 and 201812 ORDER BY month\n")
    List<Map<String, Object>> test();






}
