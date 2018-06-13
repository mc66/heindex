package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
/**
* 查询某类应用品质指数
* @author shihao
* Created on 2018/6/13
*/
@Mapper
public interface AppQualityMapper {
    /**
     * 查询指定月份、指定类别的应用应用数
     * @param month    月份
     * @param category 类别
     * @return 应用数
     */
    @Select("SELECT count(1) FROM app_quality app right JOIN app_category category ON app.category = category.id LEFT JOIN app_info active ON app.id = active.id  where category.name = #{category} and app.month=#{month}")
    int getCount(@Param("month") String month, @Param("category") String category);
    /**
     * 分页查询指定月份、指定类别的应用应用品质指数
     * @param month    月份
     * @param category 类别
     * @param offset   分页查询偏移量，从0开始
     * @param rows     最多查询记录数
     * @return 应用月活集合
     */
    @Select("SELECT app.name AS app, qua.features AS features, qua.views AS views,qua.delay AS delay,\n" +
            "qua.consume AS consume,qua.experience AS experience,qua.qindex AS qindex\n" +
            "FROM app_info app right JOIN app_category category ON app.category = category.id LEFT JOIN app_quality qua ON app.id = qua.app \n" +
            "where category.name = #{category} and qua.month=#{month} ORDER BY qua.qindex desc limit #{offset}, #{rows}")
    List<Map<String, Object>> getQualityList(@Param("month") String month, @Param("category") String category, @Param("offset") int offset, @Param("rows") int rows);

}
