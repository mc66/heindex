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
    public double quaryTotal(@Param("id") Integer id,@Param("month")String month);
}
