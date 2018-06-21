package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 应用运营指数数据库访问的sql配置
 *
 * @author machao
 * Created on 2018/6/13
 */
@Mapper
public interface AppOperationsMapper {

    /**
     * 查询指定月份期数、指定类别的应用数
     *
     * @param month    月份
     * @param category 类别
     * @return 应用数
     */
    @Select("SELECT COUNT(1) \n" +
            "FROM app_operations app RIGHT JOIN app_category category ON app.category = category.id LEFT JOIN app_info active ON app.id = active.id  \n" +
            "WHERE category.name = #{category} AND app.month=#{month}")
    int countOfCategory(@Param("month") String month, @Param("category") String category);

    /**
     * 分页查询指定月份、指定类别的所有应用的运营数据
     *
     * @param month    月份
     * @param category 类别
     * @param offset   分页查询偏移量，从0开始
     * @param rows     最多查询记录数
     * @return 应用运营指数集合
     */

    @Select("SELECT app.name AS app,app.flag AS flag, ope.content AS content, ope.channel AS channel,ope.tariff AS tariff,\n" +
            "ope.service AS service,ope.market AS market,ope.experience AS experience,ope.oindex AS oindex\n" +
            "FROM app_info app RIGHT JOIN app_category category ON app.category = category.id LEFT JOIN app_operations ope ON app.id = ope.app \n" +
            "WHERE category.id = #{category} AND ope.month=#{month} ORDER BY ope.oindex DESC LIMIT #{offset}, #{rows}")
    List<Map<String, Object>> getOperationsList(@Param("month") String month, @Param("category") String category, @Param("offset") int offset, @Param("rows") int rows);
}
