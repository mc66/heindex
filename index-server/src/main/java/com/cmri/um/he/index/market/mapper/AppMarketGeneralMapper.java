package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppMarketGeneralMapper {

    /**
     * 查询人均时长和人均流量
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`length_time` as lengthTime,am.`flow` as flow,am.`month` FROM app_market am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month`")
    List<Map<String, Object>> getLengthTime(@Param("app") Integer app,@Param("month1") String month1,@Param("month2") String month2);

    /**
     * 查询应用概括统计
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT am.`total_user` AS totalUser,am.`new_user` AS newUser,am.`mau_number` AS mauNumber,am.`keep_rate` AS keepRate,am.`length_time` AS lengthTime,am.`flow` AS flow,am.`month` FROM app_market am WHERE app=#{app} AND am.`month` BETWEEN #{month1} AND #{month2} ORDER BY am.`month`\n")
    List<Map<String,Object>> quaryGeneralStatistic(@Param("app")Integer app,@Param("month1")String month1,@Param("month2")String month2);
}
