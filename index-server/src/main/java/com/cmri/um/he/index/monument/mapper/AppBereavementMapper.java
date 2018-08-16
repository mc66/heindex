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
    @Select(" SELECT count(aea.`status`) as status FROM app_emotion_analyze aea WHERE aea.category=#{category} and aea.`month` BETWEEN #{startTime} AND #{endTime} and aea.`status`=1 GROUP BY aea.app_name ")
    List<Map<String,Object>> getNumberCommentsJust(@Param("category") Integer category,@Param("startTime") String startTime,@Param("endTime") String endTime);
    @Select(" SELECT aea.app_name FROM app_emotion_analyze aea WHERE aea.category=#{category} and aea.`month` BETWEEN #{startTime} AND #{endTime} GROUP BY aea.app_name ")
    List<Map<String,Object>> findAppName(@Param("category") Integer category,@Param("startTime") String startTime,@Param("endTime") String endTime);
    @Select(" SELECT count(aea.`status`) as status FROM app_emotion_analyze aea WHERE aea.category=#{category} and aea.`month` BETWEEN #{startTime} AND #{endTime} and aea.`status`=0 GROUP BY aea.app_name ")
    List<Map<String,Object>> findNumberCommentsCentre(@Param("category") Integer category,@Param("startTime") String startTime,@Param("endTime") String endTime);
    @Select(" SELECT count(aea.`status`) as status FROM app_emotion_analyze aea WHERE aea.category=#{category} and aea.`month` BETWEEN #{startTime} AND #{endTime} and aea.`status`=-1 GROUP BY aea.app_name ")
    List<Map<String,Object>> findNumberCommentsLoad(@Param("category") Integer category,@Param("startTime") String startTime,@Param("endTime") String endTime);
}
