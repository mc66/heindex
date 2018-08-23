package com.cmri.um.he.index.terminal.service;


import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.receivable.CommentParticularsVO;

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
    public List<Map<String,Object>> quaryProvince();

    /**
     * 终端指数Top10
     * @param id
     * @param month
     * @return
     */
    public List<Map<String,Object>> quaryTerminalExponent(Integer id,String month);
}
