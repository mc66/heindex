package com.cmri.um.he.index.mapper;

import com.cmri.um.he.index.entity.AppCalculationQualityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * author shihao
 * Created on 2018/6/27
 */
@Mapper
public interface AppOriginalCalculationMapper {

    @Update("update app_calculation_quality set features=#{features}, view=#{view} where app=#{app} ")
    int upadte(AppCalculationQualityEntity appCalculationQualityEntity);
}
