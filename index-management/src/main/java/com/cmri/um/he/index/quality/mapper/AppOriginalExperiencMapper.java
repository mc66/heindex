package com.cmri.um.he.index.quality.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;
/**
 * 查询某类未添加应用体验接口数据库访问的sql配置
 *
 * @author limin
 * Created on 2018/6/26
 */
@Mapper
public interface AppOriginalExperiencMapper {

    /**
     *  查询所有的体验数据
     * @return
     */
    @Select(" SELECT acq.id, acq.`month`,ac.`name` AS category,ai.`name` AS app,acq.version,acq.experience FROM app_calculation_quality acq LEFT JOIN app_category ac on acq.category=ac.id LEFT JOIN app_info ai on acq.app=ai.id ")
    List<Map<String,Object>> findWhole();
    /**
     *  is_valid
     * @param category
     * @return
     */
    @Select(" SELECT acq.id, acq.`month`,ac.`name` AS category,ai.`name` AS app,acq.version,acq.experience FROM app_calculation_quality acq \n" +
            "LEFT JOIN app_category ac on acq.category=ac.id LEFT JOIN app_info ai on acq.app=ai.id\n" +
            "WHERE ac.id=#{category} and acq.experience=0 ")
    List<Map<String,Object>> findExperience(  @Param("category") Integer category);
    /**
     *修改体验数据
     * @param id
     * @param experience
     * @return
     */
    @Update(" update app_calculation_quality acq set acq.experience = #{experience} where acq.id=#{id} ")
    int updateExperience(@Param("id")Integer id, @Param("experience")Double experience);
}
