package com.cmri.um.he.index.terminal.mapper;

import com.cmri.um.he.index.receivable.CommentParticularsVO;
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
            +"</script>")
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
    public int quaryTotal(Integer id,String month);

    /**
     * 查询指定月份终端型号排行榜
     * @param month 指定月份
     * @param start 开始条数
     * @param end   结束条数
     * @param pid   省份id
     * @param bid   品牌id
     * @return 结果集
     * */
    @Select("<script>\n" +
            "SELECT tb.brand_name,ta.terminal_type,ta.terminal_amount,ta.basis\n" +
            "FROM `terminal_analyze` ta LEFT JOIN terminal_brand tb ON ta.brand = tb.id\n" +
            "WHERE ta.`month` = #{month} ORDER BY ta.terminal_amount LIMIT #{start},#{end}\n" +
            "<if test='pid !=null '> AND ta.`province`=#{pid} </if>\n" +
            "<if test='bid !=null '> AND ta.`province`=#{bid} </if>\n" +
            "</script>")
    List<Map<String,Object>> findBrand(@Param("month") String month,@Param("start") String start,@Param("end") String end,@Param("pid") String pid,@Param("bid") String bid);

}
