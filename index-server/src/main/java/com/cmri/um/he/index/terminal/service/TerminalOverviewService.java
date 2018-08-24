package com.cmri.um.he.index.terminal.service;


import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.receivable.CommentParticularsVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 终端指数——概览分析
 * @author machao
 * Created on 2018/8/23
 */
public interface TerminalOverviewService {

    /**
     * 查询省份
     * @return
     */
    List<Map<String,Object>> quaryProvince();

    /**
     * 终端指数Top10
     * @param id
     * @param month
     * @return
     */
     List<Map<String,Object>> quaryTerminalExponent(Integer id,String month);

    /**
     * 终端品牌
     * @return
     */
     List<Map<String,Object>> quaryBrand();


    /**
     * 查询指定月份终端型号排行榜
     * @param month 指定月份
     * @param start 开始条数
     * @param end   结束条数
     * @param pid   省份id
     * @param bid   品牌id
     * @return 结果集
     * */
    List<Map<String,Object>> findBrand(String month,int start, int end, int pid,int bid);

}
