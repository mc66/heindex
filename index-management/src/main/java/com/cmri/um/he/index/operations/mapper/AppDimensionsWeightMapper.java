package com.cmri.um.he.index.operations.mapper;

import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.entity.AppOriginalOperationsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 维度权重管理
 * @author machao
 * Created on 2018/7/17
 */
@Mapper
public interface AppDimensionsWeightMapper {

    /**
     * 查询指定类别的维度数量
     *
     * @return 应用数
     */
    @Select("SELECT COUNT(1) FROM app_dimensions ad LEFT JOIN aspect ap ON ad.`aspect_id`=ap.`id`")
    int count();

    /**
     * 分页查询所有的维度权重
     *
     * @param offset 分页查询偏移量，从0开始
     * @param rows   最多查询记录数
     * @return 维度权重集合
     */
    @Select("SELECT ad.`id`,ap.`aspect`,ad.`dimensions`,ad.`weight` FROM app_dimensions ad LEFT JOIN aspect ap ON ad.`aspect_id` = ap.`id` \n" +
            "             LIMIT #{offset}, #{rows}")
    List<Map<String, Object>> getAllDimensions(@Param("offset") int offset, @Param("rows") int rows);

    /**
     * 根据类别id查询当前类所有维度权重数量
     * @param aspect
     * @return
     */
    @Select("SELECT COUNT(1) FROM app_dimensions ad LEFT JOIN aspect ap ON ad.`aspect_id`=ap.`id` WHERE ad.`aspect_id`=#{aspect}")
    int countOfCategory(@Param("aspect") int aspect);

    /**
     * 分页查询指定类所有维度权重
     *
     * @param aspect 类别
     * @param offset   分页查询偏移量，从0开始
     * @param rows     最多查询记录数
     * @return 维度权重集合
     */
    @Select("SELECT ad.`id`,ap.`aspect`,ad.`dimensions`,ad.`weight` FROM app_dimensions ad LEFT JOIN aspect ap ON ad.`aspect_id` = ap.`id` \n" +
            "            WHERE ad.`aspect_id`=#{aspect}  LIMIT #{offset}, #{rows}")
    List<Map<String, Object>> getAllDimensionsByAspect(@Param("aspect") int aspect, @Param("offset") int offset, @Param("rows") int rows);

    /**
     * 修改维度权重
     * @param id
     * @param weight
     * @return
     */
    @Update("UPDATE app_dimensions SET weight = #{weight} WHERE id = #{id}")
    void updateWeight(@Param("id") int id,@Param("weight") double weight);
}
