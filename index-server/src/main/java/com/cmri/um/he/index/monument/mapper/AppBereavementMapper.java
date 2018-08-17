package com.cmri.um.he.index.monument.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


/**
 * 情感指数变化趋势的sql配置
 *
 * @author yinjunjun
 * Created on 2018/8/10
 */
@Mapper
public interface AppBereavementMapper {

    /**
     * 查询指定月份区间的情感趋势变化
     * @param category 类别id
     * @param startTime 开始月份
     * @param endTime 结束月份
     * @return 结果集
     * */
    @Select("SELECT emotion.`month`,info.`name` name,cate.`name`,emotion.emotion_score value FROM app_emotion_parameter emotion\n" +
            "LEFT JOIN app_info info ON emotion.app = info.id\n" +
            "LEFT JOIN app_category cate ON emotion.category = cate.id\n" +
            "WHERE emotion.category = #{category} AND emotion.`month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String,Object>> findBereavement(@Param("category") Integer category,@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 查询指定月份区间的情感趋势变化
     * @param id appid
     * @param startTime 开始月份
     * @param endTime 结束月份
     * @return 结果集
     * */
    @Select("SELECT emotion.emotion_score\n" +
            "FROM app_emotion_parameter emotion LEFT JOIN app_info info ON emotion.app = info.id\n" +
            "WHERE emotion.app = #{id} AND emotion.`month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String,Object>> findBereavements(@Param("id") Integer id,@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 查询指定月份的情感指数
     * @param category 类别id
     * @param endTime 指定月份
     * @return 结果集
     * */
    @Select("SELECT emotion.`month`,info.`name`,cate.`name`,emotion.emotion_score FROM app_emotion_parameter emotion\n" +
            "LEFT JOIN app_info info ON emotion.app = info.id\n" +
            "LEFT JOIN app_category cate ON emotion.category = cate.id\n" +
            "WHERE emotion.category = #{category} AND emotion.`month` = #{endTime}")
    List<Map<String,Object>> findMoonEmotion(@Param("category") Integer category,@Param("endTime") String endTime);

    /**
     * 查询指定类下的所有app
     * @param category 指定类别
     * @return 结果集
     * */
    @Select("SELECT info.`name` name,info.id id FROM app_category cate LEFT JOIN app_info info \n" +
            "ON cate.id = info.category WHERE cate.id = #{category}")
    List<Map<String,Object>> findCategory(Integer category);

    /**
     * 查询评论热词  词频统计
     * @param app 指定app
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 结果集
     * */
    @Select("SELECT word_name name,SUM(word_frequency) value\n" +
            "FROM hot_word WHERE app =#{app}\n" +
            " AND date BETWEEN #{startTime} AND #{endTime}\n" +
            "GROUP BY word_name")
    List<Map<String,Object>> frequencyCount(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);


    /**
     * 查询正性评论总条数
     * @param app 指定app
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 结果集
     * */
    @Select("SELECT freq_positive,freq_negativity,freq_neutral,freq_sum\n" +
            "FROM app_emotion_parameter WHERE app = #{app} AND `month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String,Object>> findPositive(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);

   /* *//**
     * 查询负性评论总条数
     * @param category 指定类别
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 结果集
     * *//*
    @Select("SELECT ai.`name` appName,SUM(ap.freq_negativity) value,SUM(ap.freq_sum) rate\n" +
            "FROM app_emotion_parameter ap LEFT JOIN app_category ca ON ap.category = ca.id\n" +
            "LEFT JOIN app_info ai ON ap.app = ai.id \n" +
            "WHERE ap.category = #{category} AND ap.`month` BETWEEN #{startTime} AND #{endTime}\n" +
            "GROUP BY  ai.`name` ")
    List<Map<String,Object>> findNegativity(@Param("category") Integer category,@Param("startTime") String startTime,@Param("endTime") String endTime);

    *//**
     * 查询中性评论总条数
     * @param category 指定类别
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 结果集
     * *//*
    @Select("SELECT ai.`name` appName,SUM(ap.freq_neutral) value,SUM(ap.freq_sum) rate\n" +
            "FROM app_emotion_parameter ap LEFT JOIN app_category ca ON ap.category = ca.id\n" +
            "LEFT JOIN app_info ai ON ap.app = ai.id \n" +
            "WHERE ap.category = #{category} AND ap.`month` BETWEEN #{startTime} AND #{endTime}\n" +
            "GROUP BY  ai.`name` ")
    List<Map<String,Object>> findNeutral(@Param("category") Integer category,@Param("startTime") String startTime,@Param("endTime") String endTime);
*/
}
