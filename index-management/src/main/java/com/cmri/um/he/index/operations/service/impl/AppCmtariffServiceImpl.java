package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppCmtariffDao;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.entity.AppOriginalOperationsEntity;
import com.cmri.um.he.index.operations.service.AppCmtariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 	渠道、营销、资费、服务原始数据
 *
 * @author limin
 * Created on 2018/7/10
 */
@Service
public class AppCmtariffServiceImpl implements AppCmtariffService {

    @Autowired
    private AppCmtariffDao appCmtariffDao;

    @Override
    public boolean saveAppOriginalContentEntity(List<AppOriginalOperationsEntity> list) {

        for (AppOriginalOperationsEntity entity :list) {
            entity.setAtime(new Date());
            appCmtariffDao.saveAppOriginalContentEntity(entity);
        }
        return true;
    }

    @Override
    public boolean updateAppOriginalContentEntity(List<AppOriginalOperationsEntity> list) {
        for (AppOriginalOperationsEntity ao: list) {
            int bars = appCmtariffDao.queryAppCalculationOperationsEntityByMeasureValue();
            int total= appCmtariffDao.queryAppCalculationOperationsEntityByDimensionsId(ao.getDimensionsId());
            AppCalculationOperationsEntity aco = new AppCalculationOperationsEntity();
           if (total ==0){
               double channel = 0.00;
               double tariff = 0.00;
               double ser= 0.00;
               double market= 0.00;
               aco.setChannel(channel);
               aco.setTariff(tariff);
               aco.setService(ser);
               aco.setMarket(market);
               List<Map<String,Object>> dataList = appCmtariffDao.queryAppCalculationOperations(ao.getVersion(),ao.getApp(),ao.getMonth());
               if (dataList == null){
                   appCmtariffDao.saveAppCalculationOperations(aco);
               }else {
                   appCmtariffDao.updateAppCalculationOperations(aco.getChannel(),aco.getMarket(),aco.getService(),aco.getTariff(),ao.getVersion(),ao.getApp(),ao.getMonth());
               }
           }else {
               double channel=new BigDecimal((float)bars/total*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
               double tariff=new BigDecimal((float)bars/total*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
               double ser=new BigDecimal((float)bars/total*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
               double market=new BigDecimal((float)bars/total*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
               aco.setChannel(channel);
               aco.setTariff(tariff);
               aco.setService(ser);
               aco.setMarket(market);
               List<Map<String,Object>> dataList = appCmtariffDao.queryAppCalculationOperations(ao.getVersion(),ao.getApp(),ao.getMonth());
               if (dataList.size()==0){
                   appCmtariffDao.saveAppCalculationOperations(aco);
               }else {
                   appCmtariffDao.updateAppCalculationOperations(aco.getChannel(),aco.getMarket(),aco.getService(),aco.getTariff(),ao.getVersion(),ao.getApp(),ao.getMonth());
               }
           }

        }

        return true;
    }
}
