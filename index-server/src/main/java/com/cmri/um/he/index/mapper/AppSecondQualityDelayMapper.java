package com.cmri.um.he.index.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 二级页面时延功耗折线图
 * @author lch
 * Created on 2018/07/02 16:13
 */
@Mapper
public interface AppSecondQualityDelayMapper {
    /**
     * 根据测量指标、网络环境、月份来查询测量值
     * @param id   app的id
     * @param measuring  测量指标
     * @param network    网络环境
     * @param month      月份
     * @return
     */
    @Select("SELECT app.name,app.flag,delay.measure FROM app_original_delay delay JOIN app_info app ON delay.app=app.id WHERE delay.measuring=#{measuring} AND delay.network=#{network} " +
            "AND delay.category=(SELECT category.id FROM app_category category LEFT JOIN app_info info ON category.id = info.category WHERE info.id = #{id} ) AND delay.`month`=#{month} ORDER BY measure DESC")
    List<Map<String,Object>> findQualityMeasureBySome(@Param("id") int id, @Param("measuring") String measuring,@Param("network") String network, @Param("month")String month);
}
