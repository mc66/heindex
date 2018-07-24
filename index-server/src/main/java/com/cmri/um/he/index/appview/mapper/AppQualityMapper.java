package com.cmri.um.he.index.appview.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
/**
* 查询某类应用品质指数
* @author shihao
* Created on 2018/6/13
*/
@Mapper
public interface AppQualityMapper {
    /**
     * 查询指定月份、指定类别的应用应用数
     * @param month    月份
     * @param category 类别
     * @return 应用数
     */
    @Select("SELECT count(1) FROM app_quality app right JOIN app_category category ON app.category = category.id LEFT JOIN app_info active ON app.id = active.id  where category.name = #{category} and app.month=#{month}")
    int getCount(@Param("month") String month, @Param("category") String category);
    /**
     * 分页查询指定月份、指定类别的应用应用品质指数
     * @param month    月份
     * @param category 类别
     * @param offset   分页查询偏移量，从0开始
     * @param rows     最多查询记录数
     * @return 应用月活集合
     */
    @Select("SELECT app.id AS app_id ,app.name AS app,app.flag AS flag, qua.features AS features, qua.views AS views,qua.delay AS delay,\n" +
            "qua.consume AS consume,qua.experience AS experience,qua.qindex AS qindex\n" +
            "FROM app_info app right JOIN app_category category ON app.category = category.id LEFT JOIN app_quality qua ON app.id = qua.app \n" +
            "where category.id = #{category} and qua.month=#{month} ORDER BY qua.qindex desc limit #{offset}, #{rows}")
    List<Map<String, Object>> getQualityList(@Param("month") String month, @Param("category") String category, @Param("offset") int offset, @Param("rows") int rows);

    /**
     * 查询单个app的月份数据
     * @param id app的id
     * @param month 月份
     * @param month2 前两月份
     * @return
     */
    @Select("SELECT * FROM app_quality q WHERE q.app=#{id} and q.`month` BETWEEN #{month2} and #{month}" )
    List<Map<String, Object>> getQualityData(@Param("id") int id, @Param("month2") String month2, @Param("month") String month );

    /**
     *  查询功能指标
     * @param app
     * @param month
     * @return
     */
    @Select("  SELECT aq.id,ai.`name` as app,aq.features,ai.flag FROM app_quality aq LEFT JOIN app_info ai ON aq.app = ai.id WHERE aq.category= (SELECT a.category FROM app_info a WHERE id = #{app} ) and aq.`month`= #{month} ORDER BY aq.features DESC \n ")
    List<Map<String,Object>> queryQualityFeatures(@Param("app")Integer app,@Param("month") String month);

    /**
     *  查询界面指标
     * @param app
     * @param month
     * @return
     */
    @Select("  SELECT aq.id,ai.`name` as app,aq.views,ai.flag FROM app_quality aq LEFT JOIN app_info ai ON aq.app = ai.id WHERE aq.category= (SELECT a.category FROM app_info a WHERE id = #{app} ) and aq.`month`= #{month} ORDER BY aq.views DESC \n ")
    List<Map<String,Object>> queryQualityViews(@Param("app")Integer app,@Param("month") String month);

    /**
     *  查询时延指标
     * @param app
     * @param month
     * @return
     */
    @Select("  SELECT aq.id,ai.`name` as app,aq.delay,ai.flag FROM app_quality aq LEFT JOIN app_info ai ON aq.app = ai.id WHERE aq.category= (SELECT a.category FROM app_info a WHERE id = #{app} ) and aq.`month`= #{month} ORDER BY aq.delay DESC \n ")
    List<Map<String,Object>> queryQualityDelay(@Param("app")Integer app,@Param("month") String month);

    /**
     *  查询功耗指标
     * @param app
     * @param month
     * @return
     */
    @Select("  SELECT aq.id,ai.`name` as app,aq.consume,ai.flag FROM app_quality aq LEFT JOIN app_info ai ON aq.app = ai.id WHERE aq.category= (SELECT a.category FROM app_info a WHERE id = #{app} ) and aq.`month`= #{month} ORDER BY aq.consume DESC \n ")
    List<Map<String,Object>> queryQualityConsume(@Param("app")Integer app,@Param("month") String month);

    /**
     *  查询使用体验
     * @param app
     * @param month
     * @return
     */
    @Select("  SELECT aq.id,ai.`name` as app,aq.experience,ai.flag FROM app_quality aq LEFT JOIN app_info ai ON aq.app = ai.id WHERE aq.category= (SELECT a.category FROM app_info a WHERE id = #{app} ) and aq.`month`= #{month} ORDER BY aq.experience DESC \n ")
    List<Map<String,Object>> queryQualityExperience(@Param("app")Integer app,@Param("month") String month);

    /**
     * 查询app的logo
     * @param appId
     * @return
     */
    @Select("SELECT ai.`name`,al.`logo` FROM app_logo al JOIN app_info ai ON al.`app`=ai.`id` WHERE app=#{appId}")
    List<Map<String,Object>> getAppLogo(@Param("appId")int appId);

}
