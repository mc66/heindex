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
    @Select("SELECT emotion.`month`,info.`name`,cate.`name`,emotion.emotion_score FROM app_emotion_parameter emotion\n" +
            "LEFT JOIN app_info info ON emotion.app = info.id\n" +
            "LEFT JOIN app_category cate ON emotion.category = cate.id\n" +
            "WHERE emotion.category = #{category} AND emotion.`month` BETWEEN #{startTime} AND #{endTime}")
    List<Map<String,Object>> findBereavement(@Param("category") Integer category,@Param("startTime") String startTime,@Param("endTime") String endTime);
    /**
     * 查询指定月份的情感指数
     * @param category 类别id
     * @param startTime 指定月份
     * @return 结果集
     * */
    @Select("SELECT emotion.`month`,info.`name`,cate.`name`,emotion.emotion_score FROM app_emotion_parameter emotion\n" +
            "LEFT JOIN app_info info ON emotion.app = info.id\n" +
            "LEFT JOIN app_category cate ON emotion.category = cate.id\n" +
            "WHERE emotion.category = #{category} AND emotion.`month` = #{startTime}")
    List<Map<String,Object>> findMoonEmotion(@Param("category") Integer category,@Param("startTime") String startTime);

    /**
     * 查询指定类下的所有app
     * @param category 指定类别
     * @return 结果集
     * */
    @Select("SELECT info.`name` FROM app_category cate LEFT JOIN app_info info \n" +
            "ON cate.id = info.category WHERE cate.id = #{category}")
    List<String> findCategory(Integer category);
}
