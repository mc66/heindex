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
     * @param category 类别
     * @return 应用月活集合
     */
    @Select("select emotion.id,info.name AS app,info.flag,emotion.emotion,emotion.month,emotion.atime FROM app_emotion emotion LEFT JOIN app_category category ON emotion.category = category.id LEFT JOIN app_info info ON emotion.app = info.id where category.id = #{category} AND emotion.`month` in(SELECT x.`month` FROM (SELECT distinct `month` FROM app_emotion emotion WHERE emotion.category=#{category} GROUP BY `month` DESC LIMIT 0,4)as x )")
    List<Map<String,Object>> findByCategory(@Param("category") Integer category);
}
