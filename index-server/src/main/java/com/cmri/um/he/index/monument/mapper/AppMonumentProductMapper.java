package com.cmri.um.he.index.monument.mapper;

import com.cmri.um.he.index.monument.entity.AppEmotionAnalyzeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface AppMonumentProductMapper {

    /**
     * 查询日正性评论数
     * @param app
     * @param date
     * @return
     */
    @Select("SELECT COUNT(IF(aea.`status`=1,TRUE,NULL))AS freqPositive,COUNT(IF(aea.`status`=-1,TRUE,NULL))AS freqNegativity,COUNT(IF(aea.`status`=NULL,TRUE,NULL))AS freqNeutral FROM app_emotion_analyze aea WHERE app=#{app} AND aea.`month`=#{date} ")
    public Map<String,Integer> quaryDayCommentAmount(@Param("app") Integer app,@Param("date") String date);

    /**
     * 查询日正性评论数
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT COUNT(IF(aea.`status`=1,TRUE,NULL))AS freqPositive,COUNT(IF(aea.`status`=-1,TRUE,NULL))AS freqNegativity,COUNT(IF(aea.`status`=NULL,TRUE,NULL))AS freqNeutral FROM app_emotion_analyze aea WHERE app=#{app} AND aea.`month` BETWEEN #{startTime} AND #{endTime}  ")
    public Map<String,Object> quaryCommentAmount(@Param("app") Integer app,@Param("startTime") String startTime,@Param("endTime") String endTime);
}
