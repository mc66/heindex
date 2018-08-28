package com.cmri.um.he.index.terminal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    public List<Map<String,Object>> quaryProvince();

    /**
     * 终端指数Top10
     * @param id
     * @param month
     * @return
     */
    @Select("<script>"
            +"SELECT tb.`brand_name`AS brandName,ta.`month`,SUM(ta.`terminal_amount`)AS amount "
            +"FROM terminal_ana⁪lyze ta JOIN terminal_brand tb ON ta.`brand`=tb.`id` "
            + "WHERE ta.`month`=#{month}"
            +"<if test='id !=null '> AND ta.`province`=#{id} </if>"
            +"GROUP BY ta.`province` ORDER BY amount DESC</script>")
    public List<Map<String,Object>> quaryTerminalExponent(@Param("id")Integer id,@Param("month")String month);

    /**
     * 所有终端数量总和
     * @param id
     * @param month
     * @return
     */
    @Select("<script>"
            +"SELECT SUM(ta.`terminal_amount`)AS total "
            +"FROM terminal_ana⁪lyze ta JOIN terminal_brand tb ON ta.`brand`=tb.`id` "
            + "WHERE ta.`month`=#{month}"
            +"<if test='id !=null '> AND ta.`province`=#{id} </if>"
            +"</script>")
    public double quaryTotal(@Param("id")Integer id, @Param("month")String month);

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
            "SELECT tb.brand_name,ta.terminal_type,ta.terminal_amount,ta.basis\n" +
            "FROM terminal_ana⁪lyze ta LEFT JOIN terminal_brand tb ON ta.brand = tb.id\n" +
            "WHERE ta.`month` = #{month}\n" +
            "<if test='pid != null'> AND ta.`province`=#{pid} </if>\n" +
            "<if test='bid != null'> AND ta.`brand`=#{bid} </if> " +
            "ORDER BY ta.terminal_amount LIMIT #{offset},#{rows}\n" +
            "</script>")
    List<Map<String,Object>> findBrand(@Param("month") String month,@Param("offset") int offset,@Param("rows") int rows,@Param("pid") Integer pid,@Param("bid") Integer bid);

    /**
     * 查询指定月份终端型号排行榜
     * @param month 指定月份
     * @param offset 分页查询偏移量，从0开始
     * @param rows   最多查询记录数
     * @return 结果集
     * */
    @Select("SELECT tb.brand_name,ta.terminal_type,ta.terminal_amount,ta.basis\n" +
            "FROM terminal_ana⁪lyze ta LEFT JOIN terminal_brand tb ON ta.brand = tb.id\n" +
            "WHERE ta.`month` = #{month}\n" +
            "ORDER BY ta.terminal_amount LIMIT #{offset},#{rows}")
    List<Map<String,Object>> findBrandPage(@Param("month") String month,@Param("offset") int offset,@Param("rows") int rows);


    /**
     * 查询指定月份的应用应用数
     * @param month    月份
     * @return 应用数
     */
    @Select("SELECT COUNT(id) FROM terminal_ana⁪lyze WHERE `month` = #{month}")
    int getCount(@Param("month") String month);


    /**
     * 终端品牌
     * @return
     */
    @Select("SELECT id,brand_name from terminal_brand")
    List<Map<String, Object>> quaryBrand();
}
