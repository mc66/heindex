package com.cmri.um.he.index.operations.mapper;

import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import org.apache.ibatis.annotations.*;

/**
 * 处理app运营体验得分
 * @author machao
 * Created on 2018/7/11
 */
@Mapper
public interface AppOperationExperienceMapper {

    /**
     * 根据月份，应用id和版本信息查询数据
     * @param month
     * @param app
     * @param version
     * @return
     */
    @Select("SELECT acp.`id` FROM app_calculation_operations acp WHERE MONTH=#{month} AND app=#{app} AND VERSION=#{version}")
    AppCalculationOperationsEntity find(@Param("month") String month,@Param("app") Integer app,@Param("version") String version);

    /**
     * 将已有数据的运营体验得分存入表中
     * @param id
     * @param experience
     */
    @Update("UPDATE app_calculation_operations SET experience=#{experience} WHERE id=#{id}")
    void update(@Param("id")int id,@Param("experience")double experience);


    /**
     * 将数据存入表中
     * @param appCalculationOperationsEntity
     */
    @Insert("INSERT INTO app_calculation_operations VALUES(NULL,#{month},#{app},#{category},#{version},#{content},#{channel},#{tariff},#{service},#{market},#{experience},#{status},#{atime})")
    void add(AppCalculationOperationsEntity appCalculationOperationsEntity);
}
