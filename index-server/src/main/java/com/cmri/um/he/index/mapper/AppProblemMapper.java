package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * App维度问题
 * @author machao
 * Created on 2018/7/4
 */
@Mapper
public interface AppProblemMapper {

    /**
     * 查询app某一个维度产品品质问题的方法
     * @param id
     * @param dimensions
     * @param month
     * @return
     */
    @Select("SELECT aof.`month`,ac.`name`as category,ai.`name` as app,aof.`version`,aof.`dimensions`,aof.`des`,aof.`experience`,aof.`frequency`,aof.`score`,aof.`degree` " +
            "FROM app_info ai JOIN app_original_features aof ON ai.`id`=aof.`app` LEFT JOIN app_category ac ON ai.`category`= ac.id\n" +
            "WHERE ai.`id`=#{id} AND aof.`dimensions`=#{dimensions} AND aof.`month`=#{month}")
    List<Map<String, Object>> getAppQualityProblem(@Param("id")int id,@Param("dimensions")String dimensions,@Param("month")String month);


    /**
     * 查询app某一个维度产品运营问题的方法
     * @param id
     * @param dimensions
     * @param month
     * @return
     */
    @Select("SELECT aof.`month`,ac.name,ai.`name`,aof.`version`,aof.`dimensions`,aof.`three_level_index`,aof.`specific_channel`,aof.`detailed_description`,aof.`test_value` " +
            "FROM app_info ai JOIN app_original_features aof ON ai.`id`=aof.`app` LEFT JOIN app_category ac ON ai.`category`= ac.id\n" +
            "WHERE ai.`id`=#{id} AND aof.`dimensions`=#{dimensions} AND aof.`month`=#{month}")
    List<Map<String, Object>> getAppOperationProblem(@Param("id")int id,@Param("dimensions")String dimensions,@Param("month")String month);

}
