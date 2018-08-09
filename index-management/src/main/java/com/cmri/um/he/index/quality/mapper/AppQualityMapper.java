package com.cmri.um.he.index.quality.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 查询未计算体验指数的所有应用，计算应用的品质总分
 * @author machao
 * Created on 2018/6/28
 */
@Mapper
public interface AppQualityMapper {

    /**
     * 根据qindex是否为null查询所有计算的应用计算值数据的方法
     * @return
     */
    @Select("SELECT ai.`name` as name,aq.`app` as app,aq.`category` as category,aq.`features` as features,aq.`views` as views,aq.`delay` as delay,aq.`consume` as consume,aq.`experience` as experience,aq.`version` as version,aq.`month`AS month,aq.`qindex`AS qindex\n" +
            "FROM app_info AS ai JOIN app_quality AS aq ON ai.`id`=aq.`app` \n" +
            "WHERE aq.`qindex` IS NULL")
    List<Map<String, Object>> quaryQualityList();


    /**
     * 将计算好的应用品质得分存入数据库
     * @param app
     * @param version
     * @param month
     * @param qindex
     * @return
     */
    @Update("UPDATE app_quality SET qindex = #{qindex} WHERE app=#{app} AND VERSION = #{version} AND month=#{month}")
    int setQindex(@Param("app") int app,@Param("version")String version,@Param("month")String month,@Param("qindex")double qindex);

    /**
     * 查询应用类别
     * @param category
     * @return 应用类别id
     */
    @Select("SELECT id from app_category WHERE `name`=#{category}")
    int findIdByCategoryName(String category);

    /**
     * 查询应用类别
     * @param app
     * @return 应用类别id
     */
    @Select("SELECT id from app_info WHERE `name`=#{app}")
    int findIdByAppName(String app);
}
