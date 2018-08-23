package com.cmri.um.he.index.reputation.mapper;

import com.cmri.um.he.index.receivable.CommentParticularsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间通用方法
 * @author machao
 * Created on 2018/8/10
 */
@Mapper
public interface AppProductMapper {

    /**
     * 查询日各情感倾向评论数
     * @param app
     * @param date
     * @return
     */
    @Select("SELECT COUNT(IF(aea.`status`=1,TRUE,NULL))AS freqPositive,COUNT(IF(aea.`status`=-1,TRUE,NULL))AS freqNegativity,COUNT(IF(aea.`status`=NULL,TRUE,NULL))AS freqNeutral FROM app_emotion_analyze aea WHERE app=#{app} AND aea.`month`=#{date} ")
    public Map<String,Object> quaryDayCommentAmount(@Param("app") Integer app,@Param("date") String date);


    /**
     * 半月查询各情感倾向评论数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT COUNT(IF(aea.`status`=1,TRUE,NULL))AS freqPositive,COUNT(IF(aea.`status`=-1,TRUE,NULL))AS freqNegativity,COUNT(IF(aea.`status`=NULL,TRUE,NULL))AS freqNeutral FROM app_emotion_analyze aea WHERE app=#{app} AND aea.`month` BETWEEN #{startTime} AND #{endTime}  ")
    public Map<String,Object> quaryCommentAmount(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 分页查询评论详情
     * @param commentParticularsVO
     * @return
     */
    @Select("<script>"
            +"SELECT aea.`month`,aea.`comment`,aea.`user_name` AS author,aea.`comment_source` AS source,aea.`status` "
            +"FROM app_emotion_analyze aea WHERE aea.`app`=#{app} AND aea.`month` BETWEEN #{startTime} AND #{endTime} "
            +"<if test='word !=null '> AND aea.`comment` LIKE CONCAT('%',#{word},'%') </if> "
            +"<if test='status !=null '> AND aea.`status`=#{status} </if> "
            +"<if test='commentSource !=null '> AND aea.`comment_source`= #{commentSource} </if>"
            +"ORDER BY aea.`month` DESC limit #{offset}, #{rows} "
            +"</script>")
    public List<Map<String, Object>> quaryCommentParticulars(CommentParticularsVO commentParticularsVO);

    /**
     * 查询评论个数
     * @param commentParticularsVO
     * @return
     */
    @Select("<script>SELECT COUNT(0) FROM app_emotion_analyze aea WHERE aea.`app`=#{app} AND aea.`month` BETWEEN #{startTime} AND #{endTime} <if test='word !=null '> AND aea.`comment` LIKE CONCAT('%',#{word},'%') </if><if test='status !=null '> AND aea.`status`=#{status} </if><if test='commentSource !=null '>AND aea.`comment_source`= #{commentSource} </if></script>")
    public int count(CommentParticularsVO commentParticularsVO);

    /**
     * 查询热词
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT hw.`word_name`,SUM(hw.`word_frequency`) FROM hot_word hw WHERE hw.`app`=#{app} AND  hw.`date` BETWEEN #{startTime} AND #{endTime} GROUP BY hw.`word_name` ORDER BY SUM(hw.`word_frequency`) DESC")
    public List<String> quaryHotWord(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 查询评论来源
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT DISTINCT aea.`comment_source` FROM app_emotion_analyze aea WHERE app=#{app} AND aea.`month` BETWEEN #{startTime} AND #{endTime}")
    public List<String> quaryCommentSource(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);
}
