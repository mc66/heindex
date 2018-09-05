package com.cmri.um.he.index.quality.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author lch
 *         Created on 2018/08/13 11:11
 */
@Mapper
public interface AppExcelMapper {
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
    @Select(" select DISTINCT month from app_original_operations ")
    List<Map<String,Object>> getMonth();
    @Select(" select DISTINCT f.app , i.`name` appName,c.`name` categoryName from app_original_operations f LEFT JOIN app_info i ON f.app=i.id  LEFT JOIN app_category c ON c.id=f.category WHERE `month`=#{month} ")
    List<Map<String,Object>> getAPP(@Param("month") String month);

}
