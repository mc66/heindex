package com.cmri.um.he.index.reputation.mapper;

import com.cmri.um.he.index.receivable.CommentParticularsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间
 * @author limin
 * Created on 2018/8/16
 */
@Mapper
public interface AppDetailsWordsMapper {
    /**
     * 查询日各情感倾向评论数
     * @param comment
     * @param date
     * @return
     */
    @Select(" SELECT COUNT(IF(aea.`status`=1,TRUE,NULL))AS freqPositive,COUNT(IF(aea.`status`=-1,TRUE,NULL))AS freqNegativity,COUNT(IF(aea.`status`=NULL,TRUE,NULL))AS freqNeutral FROM app_emotion_analyze aea WHERE aea.`comment` LIKE CONCAT('%',#{comment},'%') AND aea.`month`=#{date}  ")
    Map<String,Object> quaryDayqQuantitative(@Param("comment") String comment,@Param("date") String date);
    /**
     * 半月查询各情感倾向评论数
     * @param comment
     * @param startTime
     * @param endTime
     * @return
     */
    @Select(" SELECT COUNT(IF(aea.`status`=1,TRUE,NULL))AS freqPositive,COUNT(IF(aea.`status`=-1,TRUE,NULL))AS freqNegativity,COUNT(IF(aea.`status`=NULL,TRUE,NULL))AS freqNeutral FROM app_emotion_analyze aea WHERE aea.`comment` LIKE CONCAT('%',#{comment},'%') AND aea.`month` BETWEEN #{startTime} AND #{endTime} ")
    Map<String,Object> quaryquantitative(@Param("comment")String comment,@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     *  查询详情条数
     * @param commentParticularsVO
     * @return
     */

    @Select(" <script>SELECT COUNT(0) FROM app_emotion_analyze aea WHERE aea.`comment` LIKE CONCAT('%',#{word},'%') AND aea.`month` BETWEEN #{startTime} AND #{endTime} <if test='status !=null '> AND aea.`status`=#{status} </if><if test='commentSource !=null '>AND aea.`comment_source`= #{commentSource} </if></script> ")
    int count(CommentParticularsVO commentParticularsVO);

    /**
     *   分页查询详情
     * @param commentParticularsVO
     * @return
     */
    /**
     *   分页查询详情
     * @param commentParticularsVO
     * @return
     */
    @Select(" <script> SELECT aea.`month`,aea.`comment`,aea.`user_name` AS author,aea.`comment_source` AS source,aea.`status` FROM app_emotion_analyze aea WHERE aea.`comment` LIKE CONCAT('%',#{word},'%') AND aea.`month` BETWEEN #{startTime} AND #{endTime} <if test='status !=null '> AND aea.`status`=#{status} </if><if test='commentSource !=null '>AND aea.`comment_source`= #{commentSource} </if> limit #{offset}, #{rows}  </script> ")
    List<Map<String,Object>> quaryCommentParticulars(CommentParticularsVO commentParticularsVO);

    /**
     *   查询热词
     * @param startTime
     * @param endTime
     * @return
     */
    @Select(" SELECT aea.word_name FROM hot_word aea WHERE aea.date BETWEEN #{startTime} AND #{endTime} GROUP BY aea.word_name ")
    List<String> quaryHotWords(@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     *  查询评论来源
     * @param startTime
     * @param endTime
     * @return
     */
    @Select(" SELECT aea.source FROM hot_word aea WHERE aea.date BETWEEN #{startTime} AND #{endTime} GROUP BY aea.source ")
    List<String> quarySourceComment(@Param("startTime") String startTime,@Param("endTime") String endTime);
}
