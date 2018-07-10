package com.cmri.um.he.index.quality.mapper;

import com.cmri.um.he.index.quality.entity.AppQualityEntity;
import com.cmri.um.he.index.quality.provider.AppCalculationDaoProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 批量增加已经处理的结果
 * @author yinjunjun
 * Created on 2018/6/28
 * */
@Mapper
public interface StandardMapper {
    /**
     * 批量添加
     * @param appQualityEntities 批量处理的集合
     * */
    @InsertProvider(type = AppCalculationDaoProvider.class,method = "saveAll")
    void saveAll(@Param("list") List<AppQualityEntity> appQualityEntities);
}
