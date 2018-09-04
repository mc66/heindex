package com.cmri.um.he.index.terminal.dao;


import com.cmri.um.he.index.receivable.CommentParticularsVO;
import com.cmri.um.he.index.reputation.mapper.AppProductMapper;
import com.cmri.um.he.index.terminal.mapper.TerminalOverviewMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 终端指数——概览分析
 * @author machao
 * Created on 2018/8/23
 */
@Repository
public class TerminalOverviewDao extends BaseDao {

    @Autowired
    private TerminalOverviewMapper terminalOverviewMapper;

    /**
     * 查询省份
     * @return
     */
    public List<Map<String,Object>> quaryProvince() {
        return terminalOverviewMapper.quaryProvince();
    }

    /**
     * 终端指数Top10
     * @param id
     * @param month
     * @return
     */
    public List<Map<String, Object>> quaryTerminalExponent(Integer id, String month) {
        return terminalOverviewMapper.quaryTerminalExponent(id,month);

    }

    /**
     * 所有终端数量总和
     * @param id
     * @param month
     * @return
     */
    public double quaryTotal(Integer id, String month) {
        return terminalOverviewMapper.quaryTotal(id,month);

    }

    /**
     * 终端品牌
     * @return
     */
    public List<Map<String, Object>> quaryBrand() {
        return terminalOverviewMapper.quaryBrand();
    }

    /**
     * 查询指定月份终端型号排行榜
     * @param month 指定月份
     * @param page     所要查询的分页，从1开始
     * @param step     每页的记录容量
     * @param pid   省份id
     * @param bid   品牌id
     * */
    public List<Map<String,Object>> findBrand(String month,int page,int step,Integer pid, Integer bid){
        return addNo(terminalOverviewMapper.findBrand(month,offset(page, step), step, pid, bid));
    }

    /**
     * 查询指定月份终端型号排行榜
     * @param month 指定月份
     * @param page     所要查询的分页，从1开始
     * @param step     每页的记录容量
     * */
    public List<Map<String,Object>> findBrandPage(String month,int page,int step){
        return addNo(terminalOverviewMapper.findBrandPage(month,offset(page, step), step));
    }

    /**
     * 为记录添加序号
     */
    public List<Map<String, Object>> addNo(List<Map<String, Object>> items) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).put("no", i);
        }
        return items;
    }

    /**
     * 查询指定月份应用个数
     * */
    public int getCount(String month) {
        return terminalOverviewMapper.getCount(month);
    }

    /**
     * 查询上个月的销售数量
     * */
    public BigDecimal quaryMonthBrand( String imei,String month){
        return terminalOverviewMapper.quaryMonthBrand(imei,month);
    }

}
