package com.cmri.um.he.index.mapper;

import com.cmri.um.he.index.entity.AppWeightQualityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 品质得分权重的sql配置
 * @author lch
 * Created on 2018/06/28 09:12
 */
@Mapper
public interface AppWeightQualityMapper {

    /**
     * 获取权重配置
     * @param category
     * @return
     */
    @Select("SELECT * FROM app_weight_quality where category=#{category}")
    AppWeightQualityEntity findQualityConfig(int category);

    /**
     * 根据应用类型查询维度权重
     * @param category
     * @return
     */
    @Select("SELECT wfeatures,wview,wdelay,wconsume,wexperience FROM app_weight_quality WHERE category=#{category}")
    AppWeightQualityEntity getWeight(int category);


    /**
     * 查找app对用的类别id
     * @param app
     * @return
     */
    @Select("SELECT * FROM app_weight_quality WHERE CATEGORY=(SELECT CATEGORY FROM app_info WHERE id=#{app})")
    AppWeightQualityEntity  findWegihtByApp(int app);
}
