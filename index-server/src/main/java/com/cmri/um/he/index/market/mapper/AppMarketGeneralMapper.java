package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppMarketGeneralMapper {

    /**
     *  当前月数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    @Select(" SELECT am.total_user,am.new_user,am.mau_number,am.length_time,am.flow,am.keep_rate FROM app_market am WHERE am.app=#{app} and am.category=#{category} and am.`month`=#{month} ")
    List<Map<String,Object>> getMarketMonth(@Param("category") int category,@Param("app") int app,@Param("month") String month);

    /**
     *  上一月数据
     * @param category
     * @param app
     * @param month2
     * @return
     */
    @Select(" SELECT am.total_user,am.new_user,am.mau_number,am.length_time,am.flow,am.keep_rate FROM app_market am WHERE am.app=#{app} and am.category=#{category} and am.`month`=#{month2} ")
    List<Map<String,Object>> getMarketMonth1(@Param("category") int category,@Param("app") int app,@Param("month2") String month2);

    /**
     *   查询month1到month2的数据
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select(" SELECT am.total_user,am.`month` FROM app_market am WHERE am.app=#{app} and am.`month` BETWEEN #{month1} and #{month2} ")
    List<Map<String,Object>> getCumulative(@Param("app") int app,@Param("month1") String month1,@Param("month2") String month2);
}
