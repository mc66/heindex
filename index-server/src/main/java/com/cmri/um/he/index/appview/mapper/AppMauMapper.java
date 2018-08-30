package com.cmri.um.he.index.appview.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 应用月活数据库访问的sql配置
 *
 * @author zhuyin
 * Created on 2018/6/4
 */
@Mapper
public interface AppMauMapper {
    /**
     * 查询指定月份的应用数
     *
     * @param month 月份，格式`MM`
     * @return 应用数
     */
    @Select("select count(1) from app_info app LEFT JOIN app_market_month active ON app.id = active.app JOIN app_logo logo ON app.id=logo.app where active.month = #{month}")
    int count(String month);

    /**
     * 分页查询指定月份的所有应用的月活
     *
     * @param month  月份
     * @param offset 分页查询偏移量，从0开始
     * @param rows   最多查询记录数
     * @return 应用月活集合
     */
    @Select("SELECT category.id AS category_id,app.id AS app_id, category.name AS category, app.name AS app, logo.logo, active.monthly_active AS mau,active.month AS month " +
            "FROM app_info app LEFT JOIN app_category category ON app.category = category.id LEFT JOIN " +
            "app_market_month active ON app.id = active.app JOIN app_logo logo ON app.id=logo.app  " +
            "where active.month=#{month} ORDER BY mau desc limit #{offset}, #{rows}")
    List<Map<String, Object>> find(@Param("month") String month, @Param("offset") int offset, @Param("rows") int rows);

    /**
     * 查询指定月份、指定类别的应用数
     *
     * @param month    月份
     * @param category 类别
     * @return 应用数
     */
    @Select("SELECT count(1) FROM app_info app right JOIN app_category category ON app.category = category.id LEFT JOIN app_market_month active ON app.id = active.app JOIN app_logo logo ON app.id=logo.app where category.id = #{category} and active.month=#{month}")
    int countOfCategory(@Param("month") String month, @Param("category") String category);

    /**
     * 分页查询指定月份、指定类别的应用的月活
     *
     * @param month    月份
     * @param category 类别
     * @param offset   分页查询偏移量，从0开始
     * @param rows     最多查询记录数
     * @return 应用月活集合
     */
    @Select("SELECT category.name AS category,category.id AS category_id,app.id AS app_id, app.name AS app, logo.logo, active.monthly_active AS mau, active.month AS month " +
            "FROM app_info app right JOIN app_category category ON app.category = category.id " +
            "LEFT JOIN app_market_month active ON app.id = active.app " +
            "JOIN app_logo logo ON app.id=logo.app " +
            "where category.id = #{category} and active.month=#{month}" +
            " ORDER BY mau desc limit #{offset}, #{rows}")
    List<Map<String, Object>> findByCategory(@Param("month") String month, @Param("category") String category, @Param("offset") int offset, @Param("rows") int rows);

    /**
     * 查询上月活跃用户数
     * @param app
     * @param time
     * @return
     */
    @Select("SELECT monthly_active FROM app_market_month amm WHERE app=#{app} AND MONTH=#{time}")
    Double quaryLastMonthMau(@Param("app")int app,@Param("time")String time);

}
