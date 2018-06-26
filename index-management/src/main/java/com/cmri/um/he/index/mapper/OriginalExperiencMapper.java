package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface OriginalExperiencMapper {

    @Select(" SELECT acq.id, acq.`month`,ac.`name` AS category,ai.`name` AS app,acq.version,acq.experience FROM app_calculation_quality acq \n" +
            "LEFT JOIN app_category ac on acq.category=ac.id LEFT JOIN app_info ai on acq.app=ai.id\n" +
            "WHERE ac.id=#{category} and acq.experience=0 ")
    List<Map<String,Object>> findExperience(  @Param("category") Integer category);
}
