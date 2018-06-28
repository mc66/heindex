package com.cmri.um.he.index.mapper;

import com.cmri.um.he.index.entity.AppWeightQualityEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 品质得分权重的sql配置
 * @author lch
 * Created on 2018/06/28 09:12
 */
public interface AppWeightQualityMapper {

    @Select("SELECT * FROM app_weight_quality")
    List<AppWeightQualityEntity> findQualityConfig();
}
