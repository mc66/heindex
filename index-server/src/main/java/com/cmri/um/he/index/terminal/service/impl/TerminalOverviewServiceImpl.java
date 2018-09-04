package com.cmri.um.he.index.terminal.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.common.CalculateDaysByDate;
import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.terminal.dao.TerminalOverviewDao;
import com.cmri.um.he.index.terminal.service.TerminalOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
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
        Double total = terminalOverviewDao.quaryTotal(id, month);
        for (Map<String, Object> map : list) {
            if (total!=0){
                BigDecimal amount = (BigDecimal)map.get("amount");
                map.put("proportion",new BigDecimal(amount.doubleValue()/total*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
            }else {
                map.put("proportion","0.00%");
            }
        }
        return list;
    }

    @Override
    public PagingData<Map<String, Object>> findBrand(String month, int page, int step, Integer pid, Integer bid) {
        List<Map<String, Object>> brand = terminalOverviewDao.findBrand(month, page, step,pid,bid);

        String date = "";
        try {
            date = DefaultTime.getDefaultTimes(Constants.MONTH, 1, month);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ratio = "";
        for (Map<String, Object> map : brand) {
            String imei = (String) map.get("imei");
            BigDecimal sale = (BigDecimal)map.get("value");
            BigDecimal sale1 = terminalOverviewDao.quaryMonthBrand(imei, date);
            ratio = (sale.subtract(sale1).divide(sale1,4, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(2)+"%";
            map.put("ratio",ratio);
        }
        return new PagingData<>(terminalOverviewDao.getCount(month),
                page,
                step,
                brand
        );
    }

    @Override
    public PagingData<Map<String, Object>> findBrandPage(String month, int page, int step) {
        List<Map<String, Object>> brandPage = terminalOverviewDao.findBrandPage(month, page, step);

        String date = "";
        try {
            date = DefaultTime.getDefaultTimes(Constants.MONTH, 1, month);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ratio = "";
        for (Map<String, Object> map : brandPage) {
            String imei = (String) map.get("imei");
            BigDecimal sale = (BigDecimal)map.get("value");
            BigDecimal sale1 = terminalOverviewDao.quaryMonthBrand(imei, date);
            ratio = (sale.subtract(sale1).divide(sale1,4, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(2)+"%";
            map.put("ratio",ratio);
        }
        Map<String, Object> ratioMap = new HashMap<>();

        return new PagingData<>(terminalOverviewDao.getCount(month),
                page,
                step,
                brandPage
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

