package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
/**
 * 应用的关键点数据库访问的sql配置
 *
 * @author limin
 * Created on 2018/6/13
 */
@Mapper
public interface AppFeaturesMapper {

   /* @Select(" SELECT COUNT(1) from app_features afe LEFT JOIN app_info ai on afe.app = ai.id where afe.month = #{month}  ")
    int count(String month);
    @Select(" SELECT afe.id,ai.name,afe.features,afe.month,afe.atime FROM app_features afe LEFT JOIN app_info ai on afe.app = ai.id  where afe.month= #{month} limit #{offset}, #{rows} ")
    List<Map<String,Object>> find(@Param("month") String month, @Param("offset") int offset, @Param("rows") int rows);*/

    /**
     *  查询指定月份、应用名的应用的关键点数据的个数
     * @param month
     * @param app
     * @return
     */
   /*@Select(" SELECT COUNT(1) from app_features afe LEFT JOIN app_info ai on afe.app = ai.id where ai.id= #{app} and afe.month = #{month}  ")
    int countOfApp(@Param("month") String month, @Param("app") int app);*/

    /**
     *  查询指定月份、应用名的应用的关键点数据
     * @param month
     * @param app
     * @return
     */
    @Select(" SELECT afe.id,ai.name as app,afe.features,afe.month,afe.atime from app_features afe LEFT JOIN app_info ai on afe.app = ai.id where ai.id= #{app} and afe.month= #{month}")
    List<Map<String,Object>> findByApp(@Param("month") String month, @Param("app") int app);

}
