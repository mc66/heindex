package com.cmri.um.he.index.mouth.mapper;

import com.cmri.um.he.index.mouth.entity.AppEmotionAnalyzeEntity;
import com.cmri.um.he.index.mouth.entity.AppEmotionParameterEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
/**
 * 	口碑指数原始数据录入
 * @author shihao
 * Created on 2018/8/14
 */
@Mapper
public interface AppEmotionAnalyzeMapper {

    /**
     * 添加某app的评论数据
     * @param entity
     * @return
     */
    @Insert("insert into app_emotion_analyze values (#{id},#{app},#{category},#{month},#{commentSource},#{actualName},#{commentAttribute},#{userScore},#{userName},#{comment},#{status},#{atime})")
    int saveMouth(AppEmotionAnalyzeEntity entity);


    /**
     * 获得所有分类
     * @return
     */
    @Select("SELECT DISTINCT category from app_emotion_analyze  ")
    List<Map<String,Object>> getCategory();

    /**
     * 获得某类下的所有app
     * @param category
     * @return
     */
    @Select("SELECT DISTINCT app from app_emotion_analyze where category =#{category}")
    List<Map<String,Object>> getAppByCategory(@Param("category") int category);

    /**
     *  获得某app的所有数据的月份
     * @param category
     * @param app
     * @return
     */
    @Select("SELECT DISTINCT LEFT(MONTH,6) month FROM app_emotion_analyze where category =#{category} and app=#{app}")
    List<Map<String,Object>> getMonth(@Param("category") int category,@Param("app") int app);
    /**
     * 查询app在某月的正中负的评论数量
     * @param category
     * @param app
     * @param month
     * @param defaultTimes
     * @return
     */
    @Select("SELECT COUNT(*) FROM app_emotion_analyze where category =#{category} and  app=#{app} and  month BETWEEN #{defaultTimes} and #{month}")
    int  getFavorableRate(@Param("category") int category,@Param("app") int app,@Param("month") String month,@Param("defaultTimes") String defaultTimes);
    @Select("SELECT COUNT(*) FROM app_emotion_analyze where `status` =1 and category =#{category} and  app=#{app} and  month BETWEEN #{defaultTimes} and #{month}")
    int  getFavorableRate1(@Param("category") int category,@Param("app") int app,@Param("month") String month,@Param("defaultTimes") String defaultTimes);
    @Select("SELECT COUNT(*) FROM app_emotion_analyze where `status` =0  and category =#{category} and  app=#{app} and  month BETWEEN #{defaultTimes} and #{month}")
    int  getFavorableRate2(@Param("category") int category,@Param("app") int app,@Param("month") String month,@Param("defaultTimes") String defaultTimes);
    @Select("SELECT COUNT(*) FROM app_emotion_analyze where `status` =-1  and category =#{category} and  app=#{app} and  month BETWEEN #{defaultTimes} and #{month}")
    int  getFavorableRate3(@Param("category") int category,@Param("app") int app,@Param("month") String month,@Param("defaultTimes") String defaultTimes);



    @Select("SELECT * from app_emotion_parameter where category = #{category} AND app= #{app} AND month =#{month}")
    AppEmotionParameterEntity selectByApp(@Param("category") int category,@Param("app") int app,@Param("month") String month);
    /**
     * 保存净好评率及正中负的个数
     * @param entity
     * @return
     */
    @Insert("INSERT into app_emotion_parameter VALUES (#{id},#{month},#{category},#{app},#{freqPositive},#{freqNegativity},#{freqNeutral},#{freqSum},#{petPositive},#{petNegativity},#{petNeutral},#{favorableRate},#{favorableRateZ},#{favorableRateT},#{mauZ},#{mauT},#{emotionRateOriginal},#{emotionRatez},#{emotionScore},#{atime})")
    int saveFavorableRate(AppEmotionParameterEntity entity);

    /**
     * 保存净好评率及正中负的个数
     * @param entity
     * @return
     */
    @Update("UPDATE app_emotion_parameter set freq_positive=#{freqPositive},freq_negativity=#{freqNegativity},freq_neutral=#{freqNeutral},freq_sum=#{freqSum},pet_positive=#{petPositive},pet_negativity=#{petNegativity},pet_neutral=#{petNeutral},favorable_rate=#{favorableRate} where category=#{category} AND app=#{app} AND month=#{month}")
    int updateFavorableRate(AppEmotionParameterEntity entity);
    /**
     * 获得类下所有月份(app_emotion_parameter)
     * @param category
     * @return
     */
    @Select("SELECT DISTINCT month from app_emotion_parameter where category=#{category} ")
    List<Map<String,Object>> getMonthByParameter(@Param("category") int category);

    /**
     * 获得该类下的某一月份下的所有app的净好评率分数
     * @param category
     * @param month
     * @return
     */
    @Select("select app,favorable_rate from app_emotion_parameter where category =#{category} and month =#{month}")
    List<Map<String,Object>> getFavorableRates(@Param("category") int category,@Param("month") String month);

    /**
     * 保存净好评率的Z分和T分
     * @param category
     * @param app
     * @param month
     * @param favorableRateZ
     * @param favorableRateT
     * @return
     */
    @Update("UPDATE app_emotion_parameter set favorable_rate_z=#{favorableRateZ} ,favorable_rate_t=#{favorableRateT} where app=#{app}  AND category=#{category} AND `month` =#{month}")
    int updateRate(@Param("category") int category,@Param("app")int app,@Param("month")String month,@Param("favorableRateZ") double favorableRateZ,@Param("favorableRateT") double favorableRateT);

    /**
     * 查询该类下的某一月份下的所有app的月活数
     * @param category
     * @param month
     * @return
     */
    @Select("SELECT app,monthly_active from app_market_month where month=#{month} AND  app in\n" +
            "(SELECT app from app_emotion_parameter where category =#{category} and month =#{month})")
    List<Map<String,Object>> getMau(@Param("category") int category,@Param("month")String month);

    /**
     * 保存在该月份下的app的月活数的Z/T分
     * @param category
     * @param app
     * @param month
     * @param mauZ
     * @param mauT
     * @return
     */
    @Update("UPDATE app_emotion_parameter set mau_z=#{mauZ} ,mau_t=#{mauT} where app=#{app}  AND category=#{category} AND `month` =#{month}")
    int updateMau(@Param("category") int category,@Param("app")int app,@Param("month")String month,@Param("mauZ") double mauZ,@Param("mauT") double mauT);

    /**
     * 获得净好评率和月活得分
     * @param category
     * @param month
     * @return
     */
    @Select("select app,mau_t, favorable_rate_t  from app_emotion_parameter where category=#{category} and month =#{month}")
    List<Map<String,Object>> getEmotionScore(@Param("category") int category,@Param("month") String month);

    /**
     * 保存情感得分
     * @param category
     * @param app
     * @param month
     * @param emotionRateOriginal
     * @param emotionRateZ
     * @param emotionScore
     * @return
     */
    @Update("UPDATE app_emotion_parameter set emotion_rate_original=#{emotionRateOriginal} ,emotion_rate_z=#{emotionRateZ}, emotion_score=#{emotionScore} where app=#{app}  AND category=#{category} AND `month` =#{month}")
    int updateEmotionScore(@Param("category") int category,@Param("app") int app,@Param("month") String month,@Param("emotionRateOriginal") double emotionRateOriginal,@Param("emotionRateZ") double emotionRateZ,@Param("emotionScore") double emotionScore);
}
