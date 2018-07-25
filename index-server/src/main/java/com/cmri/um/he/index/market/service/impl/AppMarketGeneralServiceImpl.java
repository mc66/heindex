package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.market.dao.AppMarketGeneralDao;
import com.cmri.um.he.index.market.service.AppMarketGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppMarketGeneralServiceImpl implements AppMarketGeneralService{

    @Autowired
    private AppMarketGeneralDao appMarketGeneralDao;


    @Override
    public List<Map<String, Object>> getLengthTime(Integer app, String month1, String month2) {
        if (month1==null){
            int month1s= Integer.parseInt(month2);
            month1s=month1s-12;
            month1=Integer.toString(month1s);
        }
       return appMarketGeneralDao.getLengthTime(app,month1,month2);
    }

    @Override
    public List<Map<String, Object>> quaryGeneralStatistic(Integer app, String month1, String month2) {
        if (month1==null){
            int month1s= Integer.parseInt(month2);
            month1s=month1s-12;
            month1=Integer.toString(month1s);
        }
        return appMarketGeneralDao.quaryGeneralStatistic(app,month1,month2);
    }
}
