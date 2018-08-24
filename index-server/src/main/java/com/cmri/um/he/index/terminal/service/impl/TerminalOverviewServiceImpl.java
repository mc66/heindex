package com.cmri.um.he.index.terminal.service.impl;

import com.cmri.um.he.index.terminal.dao.TerminalOverviewDao;
import com.cmri.um.he.index.terminal.service.TerminalOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 终端指数——概览分析
 * @author machao
 * Created on 2018/8/23
 */
@Service
public class TerminalOverviewServiceImpl implements TerminalOverviewService {

    @Autowired
    private TerminalOverviewDao terminalOverviewDao;

    /**
     * 终端指数Top10
     * @param id
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryTerminalExponent(Integer id, String month) {
        List<Map<String, Object>> list = terminalOverviewDao.quaryTerminalExponent(id,month);
        double total = terminalOverviewDao.quaryTotal(id, month);
        for (Map<String, Object> map : list) {
            double amount = (double)map.get("amount");
            map.put("proportion",new BigDecimal(amount/total*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findBrand(String month, int start, int end, int pid, int bid) {
        return terminalOverviewDao.findBrand(month, start, end, pid, bid);
    }

    /**
     * 查询省份
     * @return
     */
    @Override
    public List<Map<String,Object>> quaryProvince() {
        return terminalOverviewDao.quaryProvince();
    }
}

