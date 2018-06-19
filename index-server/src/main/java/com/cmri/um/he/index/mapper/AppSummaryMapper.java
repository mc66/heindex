package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 查询品质、运营总结的数据库访问的sql配置
 *
 * @author yinjunjun
 * Created on 2018/6/19
 */
@Mapper
public interface AppSummaryMapper {

    /**
     * 查询指定期数、分类的品质总结
     * @param category 类别
     * @param month    月份
     * @return 品质总结集合
     */
    @Select("SELECT des1 FROM app_summary summary LEFT JOIN app_category category on summary.category = category.id WHERE summary.category = #{category} AND summary.`month` = #{month}")
    List<Map<String,Object>> findQuality(@Param("month") String month, @Param("category") Integer category);

    /**
     * 查询指定期数、分类的运营总结
     * @param category 类别
     * @param month    月份
     * @return 运营总结集合
     */
    @Select("SELECT des2 FROM app_summary summary LEFT JOIN app_category category on summary.category = category.id WHERE summary.category = #{category} AND summary.`month` = #{month}")
    List<Map<String,Object>> findOperation(@Param("month") String month, @Param("category") Integer category);
}
