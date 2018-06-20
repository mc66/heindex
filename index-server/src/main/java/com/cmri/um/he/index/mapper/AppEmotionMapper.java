package com.cmri.um.he.index.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 某类应用情感集合的sql配置
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@Mapper
public interface AppEmotionMapper {

    /**
     * 查询指定月份、指定类别的应用情感得分
     *
     * @param month    月份
     * @param category 类别
     * @return 应用月活集合
     */
    @Select("SELECT emotion.id,ai.name,emotion.features,emotion.emotion,ai.flag,emotion.`month` from app_emotion emotion LEFT JOIN app_info ai on emotion.app = ai.id LEFT JOIN app_category category on emotion.category=category.id where category.id= #{category} and emotion.`month`= #{month}")
    List<Map<String,Object>> findByCategory(@Param("month") String month,@Param("category") Integer category);
}
