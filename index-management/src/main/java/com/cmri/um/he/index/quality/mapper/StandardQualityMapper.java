package com.cmri.um.he.index.quality.mapper;


import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 查询未处理为标准值集合的sql配置
 *
 * @author yinjunjun
 * Created on 2018/6/25
 */
@Mapper
public interface StandardQualityMapper {

    /**
     * 查询未处理为标准值
     * @param category 需要查询的类别
     * @param month 需要查询的月份
     * @return 未处理集合
     */
    @Select("SELECT * FROM app_calculation_quality acq WHERE acq.`status` = 0 AND acq.category=#{category} AND acq.`month` =#{month}"	)
    List<AppCalculationQualityEntity> unprocessedStandard(@Param("category") Integer category, @Param("month") String month);

    /**
     * 转换成功处理状态
     * @param category 需要修改的类别
     * @param month 需要修改的月份
     * */
    @Update("UPDATE app_calculation_quality set `status`=1 WHERE category=#{category} AND `month`=#{month}")
    void updateStatus(@Param("category")Integer category,@Param("month") String month);
}
