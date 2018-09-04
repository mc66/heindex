package com.cmri.um.he.index.terminal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 终端指数——概览分析
 * @author machao
 * Created on 2018/8/23
 */
@Mapper
public interface TerminalOverviewMapper {

    /**
     * 查询省份
     * @return
     */
    @Select("SELECT id,province FROM terminal_province")
    List<Map<String,Object>> quaryProvince();

    /**
     * 终端指数Top10
     * @param id
     * @param month
     * @return
     */
    @Select("<script>"
            +"SELECT tb.`brand_name`AS brandName,ta.`month`,SUM(ta.sale_number)AS amount "
            +"FROM terminal_analyzes ta JOIN terminal_brand tb ON ta.brand_id=tb.`id` "
            +"WHERE ta.`month`=#{month} "
            +"<if test='id !=null '> AND ta.province_id=#{id} </if>"
            +"GROUP BY ta.brand_id ORDER BY amount DESC</script>")
    List<Map<String,Object>> quaryTerminalExponent(@Param("id")Integer id,@Param("month")String month);

    /**
     * 所有终端数量总和
     * @param id
     * @param month
     * @return
     */
    @Select("<script>"
            +"SELECT SUM(ta.sale_number)AS total "
            +"FROM terminal_analyzes ta JOIN terminal_brand tb ON ta.brand_id=tb.`id` "
            +"WHERE ta.`month`=#{month} "
            +"<if test='id !=null '> AND ta.province_id=#{id} </if>"
            +"</script>")
    double quaryTotal(@Param("id")Integer id, @Param("month")String month);

    /**
     * 查询指定月份终端型号排行榜
     * @param month 指定月份
     * @param offset 分页查询偏移量，从0开始
     * @param rows   最多查询记录数
     * @param pid   省份id
     * @param bid   品牌id
     * @return 结果集
     * */
    @Select("<script>\n" +
            "SELECT ta.imei,tb.brand_name,tp.terminal_type,SUM(ta.sale_number) value\n" +
            "FROM terminal_analyzes ta JOIN terminal_property tp ON ta.imei = tp.imei\n" +
            "JOIN terminal_brand tb ON tp.brand = tb.id\n" +
            "WHERE ta.`month` = #{month}\n" +
            "<if test='pid != null'> AND ta.`province_id`=#{pid} </if>\n" +
            "<if test='bid != null'> AND tb.`id`=#{bid} </if> " +
            "GROUP BY ta.imei ORDER BY SUM(ta.sale_number) LIMIT #{offset},#{rows}\n" +
            "</script>")
    List<Map<String,Object>> findBrand(@Param("month") String month,@Param("offset") int offset,@Param("rows") int rows,@Param("pid") Integer pid,@Param("bid") Integer bid);

    /**
     * 查询指定月份终端型号排行榜
     * @param month 指定月份
     * @param offset 分页查询偏移量，从0开始
     * @param rows   最多查询记录数
     * @return 结果集
     * */
    @Select("SELECT ta.imei,tb.brand_name,tp.terminal_type,SUM(ta.sale_number) value\n" +
            "FROM terminal_analyzes ta JOIN terminal_property tp ON ta.imei = tp.imei\n" +
            "JOIN terminal_brand tb ON tp.brand = tb.id\n" +
            "WHERE ta.`month` = #{month}\n" +
            "GROUP BY ta.imei ORDER BY SUM(ta.sale_number) LIMIT #{offset},#{rows}")
    List<Map<String,Object>> findBrandPage(@Param("month") String month,@Param("offset") int offset,@Param("rows") int rows);

    /**
     * 查询上个月的销售数量
     * @return
     */
    @Select("SELECT SUM(sale_number) FROM terminal_analyzes WHERE imei = #{imei} AND  month = #{month}")
    BigDecimal quaryMonthBrand(@Param("imei") String imei, @Param("month") String month);

    /**
     * 查询指定月份的应用应用数
     * @param month    月份
     * @return 应用数
     */
    @Select("SELECT COUNT(id) FROM terminal_analyzes WHERE `month` = #{month}")
    int getCount(@Param("month") String month);


    /**
     * 终端品牌
     * @return
     */
    @Select("SELECT id,brand_name from terminal_brand")
    List<Map<String, Object>> quaryBrand();
}
