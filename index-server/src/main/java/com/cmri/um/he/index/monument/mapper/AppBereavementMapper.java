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
    @Select("SELECT emotion.`month` month,info.`name` name,cate.`name`,emotion.emotion_score value FROM app_emotion_parameter emotion\n" +
            "LEFT JOIN app_info info ON emotion.app = info.id\n" +
            "LEFT JOIN app_category cate ON emotion.category = cate.id\n" +
            "WHERE emotion.category = #{category} AND emotion.`month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String,Object>> findBereavement(@Param("category") Integer category,@Param("startTime") String startTime,@Param("endTime") String endTime);


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
    @Select("SELECT  DISTINCT a.id,  a.name FROM app_emotion_analyze e LEFT JOIN app_info a ON e.category  =a.category  where e.category  =#{category}")
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
            "GROUP BY word_name ORDER BY SUM(word_frequency) DESC")
    List<Map<String,Object>> frequencyCount(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);


    /**
     * 查询app在某月的正中负的评论数量
     * @param category
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT COUNT(*) FROM app_emotion_analyze where category =#{category} and  app=#{app} and  month BETWEEN #{startTime} and #{endTime}")
    int  getFavorableRate(@Param("category") int category,@Param("app") int app,@Param("startTime") String startTime,@Param("endTime") String endTime);
    @Select("SELECT COUNT(*) FROM app_emotion_analyze where status=1 AND category =#{category} AND  app=#{app} AND  month BETWEEN #{startTime} AND #{endTime}")
    int  getFavorableRate1(@Param("category") int category,@Param("app") int app,@Param("startTime") String startTime,@Param("endTime") String endTime);
    @Select("SELECT COUNT(*) FROM app_emotion_analyze where status=0 AND category =#{category} AND  app=#{app} AND  month BETWEEN #{startTime} AND #{endTime}")
    int  getFavorableRate2(@Param("category") int category,@Param("app") int app,@Param("startTime") String startTime,@Param("endTime") String endTime);
    @Select("SELECT COUNT(*) FROM app_emotion_analyze where status=-1 AND category =#{category} AND  app=#{app} AND  month BETWEEN #{startTime} AND #{endTime}")
    int  getFavorableRate3(@Param("category") int category,@Param("app") int app,@Param("startTime") String startTime,@Param("endTime") String endTime);

}
