package com.cmri.um.he.index.terminal.dao;


import com.cmri.um.he.index.receivable.CommentParticularsVO;
import com.cmri.um.he.index.reputation.mapper.AppProductMapper;
import com.cmri.um.he.index.terminal.mapper.TerminalOverviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
