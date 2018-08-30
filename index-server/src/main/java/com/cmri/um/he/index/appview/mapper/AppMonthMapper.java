package com.cmri.um.he.index.appview.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 查询报告期数的数据库访问的sql配置
 *
 * @author yinjunjun
 * Created on 2018/6/15
 */
@Mapper
public interface AppMonthMapper {

    /**
     * 查询所有报告期数
     * @return 报告期数集合
     */
    @Select("SELECT `month` from app_market_month GROUP BY `month` DESC limit 0,6")
    List<Map<String,Object>> findMonths();

    /**
     * 查询体验指数所有报告期数
     * @return 报告期数集合
     */
    @Select("SELECT `month` from app_quality GROUP BY `month`")
    List<Map<String,Object>> findExperienceMonths();

}
