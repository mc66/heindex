package com.cmri.um.he.index.quality.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
