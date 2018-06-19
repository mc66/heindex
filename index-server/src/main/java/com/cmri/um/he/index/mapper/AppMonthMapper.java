package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    @Select("SELECT `month` from app_mau GROUP BY `month` DESC")
    List<Map<String,Object>> findMonths();

}
