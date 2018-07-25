package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.market.dao.AppMarketGeneralDao;
import com.cmri.um.he.index.market.service.AppMarketGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppMarketGeneralServiceImpl implements AppMarketGeneralService {
    @Autowired
    private AppMarketGeneralDao appMarketGeneralDao;

    /**
     * 查询次月存留率
     * @return
     */
    @Override
    public List<Map<String, Object>> test(){
        return   appMarketGeneralDao.test();
    }

    /**
     * 查询月活跃用户
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Override
    public List<Map<String, Object>> getUserNumber(Integer app, String month1, String month2) {
        if(month1.equals("null")){
            int month = Integer.parseInt(month2);
            month=month-11;
            month1=Integer.toString(month);
        }
        return   appMarketGeneralDao.getUserNumber(app,month1,month2);
    }
}
