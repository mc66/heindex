package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 应用评论数据库访问的sql配置
 * @author lch
 * Created on 2018/06/13 11:22
 */
@Mapper
public interface AppCommentMapper {

    /**
     * 查询指定月份、指定类别的应用数
     *
     * @param month    月份
     * @param category 类别
     * @return 应用数
     */
    @Select("SELECT COUNT(1) FROM app_comment com  WHERE com.category=#{category} AND com.`month`=#{month}")
    int countOfCategory(@Param("month") String month, @Param("category") int category);

    /**
     * 分页查询指定月份、指定类别的应用的评论
     *
     * @param month    月份
     * @param category 类别
     * @param offset   分页查询偏移量，从0开始
     * @param rows     最多查询记录数
     * @return 应用评论集合
     */
    @Select("select com.id,com.source,com.comm,com.`month`,app.`name` AS app,logo.logo\n" +
            "FROM app_comment com JOIN app_info app ON com.app=app.id JOIN app_logo logo ON app.id=logo.app\n" +
            "WHERE com.category=#{category} AND com.month=#{month} limit #{offset}, #{rows}")
    List<Map<String, Object>> findByCategory(@Param("month") String month, @Param("category") int category, @Param("offset") int offset, @Param("rows") int rows);
}
