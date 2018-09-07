package com.cmri.um.he.index.appview.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 咪咕视频类以及所属类品质分数的sql配置
 *
 * @author yinjunjun
 * Created on 2018/07/02
 */
@Mapper
public interface AppOwnDescMapper {
    /**
     * 查询指定月份、指定类别的品质以及自有产品
     * @param id 自有产品的id
     * @param month 查询的期数
     * @return 应用类品质分数集合
     */
    @Select("SELECT info.`name` app,info.flag flag,quality.qindex qindex\n" +
            "\tFROM app_quality quality LEFT JOIN app_info info ON quality.app = info.id\n" +
            "\tLEFT JOIN app_category category ON info.category = category.id\n" +
            "\tWHERE category.id = (SELECT category.id FROM app_category category LEFT JOIN app_info info ON category.id = info.category WHERE info.id = #{id} ) \n" +
            "\tAND quality.month = #{month} ORDER BY quality.qindex DESC ")
    List<Map<String,Object>> findByAppId(@Param("id") Integer id,@Param("month") String month);

    /**
     * 查询指定月份、指定类别的运营以及自有产品
     * @param id 自有产品的id
     * @param month 查询的期数
     * @return 应用类运营分数集合
     */
    @Select("SELECT info.`name` app,info.flag flag,operations.oindex oindex\n" +
            "\tFROM app_operations operations LEFT JOIN app_info info ON operations.app = info.id\n" +
            "\tLEFT JOIN app_category category ON info.category = category.id\n" +
            "\tWHERE category.id = (SELECT category.id FROM app_category category LEFT JOIN app_info info ON category.id = info.category WHERE info.id = #{id} ) \n" +
            "\tAND operations.month = #{month} ORDER BY operations.oindex DESC ")
    List<Map<String,Object>> findByIdAndMonth(@Param("id") Integer id,@Param("month") String month);


    /**
     * 查询产品品质中的产品简介
     * @param id
     * @param month
     * @return
     */
    @Select("SELECT info1 FROM app_summary WHERE app=#{id} AND `month`=#{month}")
    List<Map<String,Object>> findAppInfo1ByIdAndMonth(@Param("id") Integer id,@Param("month") String month);

    /**
     * 查询产品运营中的产品简介
     * @param id
     * @param month
     * @return
     */
    @Select("SELECT info2 FROM app_summary WHERE app=#{id} AND `month`=#{month}")
    List<Map<String,Object>> findAppInfo2ByIdAndMonth(@Param("id") Integer id,@Param("month") String month);
}
