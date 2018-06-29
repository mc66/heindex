package com.cmri.um.he.index.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

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
    @Select("SELECT acq.id,acq.features,acq.`view`,acq.delay,acq.consume,acq.experience,acq.version,acq.`month`,acq.atime,info.id app,info.`name`,info.special,cate.id category,cate.`name` categoryName\n" +
            "FROM app_calculation_quality acq LEFT JOIN app_info info on acq.app = info.id \n" +
            "LEFT JOIN app_category cate ON acq.category = cate.id\n" +
            "WHERE acq.`status` = 0 AND acq.category=#{category} AND acq.`month` = #{month}")
    List<Map<String,Object>> unprocessedStandard(Integer category,String month);

    /**
     * 转换成功处理状态
     * @param category 需要修改的类别
     * @param month 需要修改的月份
     * */
    @Update("UPDATE app_calculation_quality set `status`=1 WHERE category=#{category} AND `month`=#{month}")
    void updateStatus(Integer category,String month);
}
