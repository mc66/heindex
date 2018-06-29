package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 查询所有的月份
 * @author yinjunjun
 * Created on 2018/6/28
 * */
@Mapper
public interface QualityMonthMapper {
    /**
     * 查询所有月份 并倒叙
     * @return 查询结果集
     * */
    @Select("SELECT `month` from app_calculation_quality GROUP BY `month` DESC ")
    List<Map<String,Object>> findMonths();
}
