package com.cmri.um.he.index.terminal.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.terminal.dao.TerminalOverviewDao;
import com.cmri.um.he.index.terminal.service.TerminalOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            BigDecimal amount = (BigDecimal)map.get("amount");
            map.put("proportion",new BigDecimal(amount.doubleValue()/total*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
        }
        return list;
    }

    @Override
    public PagingData<Map<String, Object>> findBrand(String month, int page, int step, Integer pid, Integer bid) {
        return new PagingData<>(terminalOverviewDao.getCount(month),
                page,
                step,
                terminalOverviewDao.findBrand(month, page, step,pid,bid)
        );
    }

    @Override
    public PagingData<Map<String, Object>> findBrandPage(String month, int page, int step) {
        return new PagingData<>(terminalOverviewDao.getCount(month),
                page,
                step,
                terminalOverviewDao.findBrandPage(month, page, step)
        );
    }

    /**
     * 查询省份
     * @return
     */
    @Override
    public List<Map<String,Object>> quaryProvince() {
        return terminalOverviewDao.quaryProvince();
    }

    /**
     * 终端品牌
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryBrand() {
        return terminalOverviewDao.quaryBrand();
    }
}

