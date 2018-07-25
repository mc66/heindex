package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppMarketGeneralMapper {

    /**
     * 查询次月存留率
     * @return
     */
    @Select("select category,app, month ,mau_number AS '月活跃用户',keep_rate AS '次月存留率' from app_market \n" +
            "where app=60 and category=3 and month BETWEEN 201801 and 201812 ORDER BY month\n")
    List<Map<String, Object>> test();

    /**
     * 查存月活跃用户
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT m.app , m.new_user ,m.mau_number,m.month\n" +
            "from app_market m\n" +
            "where m.app=#{app} and m.month BETWEEN #{month1} and #{month2} ORDER BY m.month")
    List<Map<String, Object>> getUserNumber(@Param("app") Integer app,@Param("month1") String  month1,@Param("month2") String  month2 );

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
