package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.market.dao.AppMarketDao;
import com.cmri.um.he.index.market.service.AppMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 市场指数的关键指标数据
 * @author shihao/machao
 * Created on 2018/6/13
 */
@Service
public class AppMarketServiceImpl implements AppMarketService {

    @Autowired
    private AppMarketDao appMarketDao;

    /**
     * 根据应用类别id以及月份查询市场指标数据
     * @param category
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Object>> getAppMarketList(int category,String month) {
        return appMarketDao.getAppMarketList(category,month);
    }

    /**
     * 查询市场指数的关键指标数据(默认月份时间)
     * @param category
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Object>> getMarket(int category, String month) {
        int month1 = Integer.parseInt(month);
        month1=month1-6;
        String month2=Integer.toString(month1);
        List<Map<String, Object>>  list=appMarketDao.getMarket(category,month,month2);
        return list;
    }

    /**
     * 查询市场指数的关键指标数据(根据月份区间)
     * @param category
     * @param month1
     * @param month2
     * @return
     */
    @Override
    public List<Map<String, Object>> getMarketList(int category, String month1, String month2) {
        List<Map<String, Object>>  list=appMarketDao.getMarketList(category,month1,month2);
        return list;
    }
}
