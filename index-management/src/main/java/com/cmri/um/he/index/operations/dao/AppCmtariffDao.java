package com.cmri.um.he.index.operations.dao;

import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.entity.AppOriginalOperationsEntity;
import com.cmri.um.he.index.operations.mapper.AppCmtariffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 	渠道、营销、资费、服务原始数据
 *
 * @author limin
 * Created on 2018/7/10
 */
@Repository
public class AppCmtariffDao extends BaseDao {

    @Autowired
    private AppCmtariffMapper appCmtariffMapper;

    public boolean saveAppOriginalContentEntity(AppOriginalOperationsEntity entity) {
         int i = appCmtariffMapper.saveAppOriginalContentEntity(entity);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    public int queryAppCalculationOperationsEntityByMeasureValue(String dimensions,int app,String month) {
        return appCmtariffMapper.queryAppCalculationOperationsEntityByMeasureValue(dimensions,app,month);
    }

    public int queryAppCalculationOperationsEntityByDimensionsId(String dimensions,int app,String month) {
        return appCmtariffMapper.queryAppCalculationOperationsEntityByDimensionsId(dimensions,app,month);
    }

    public List<Map<String,Object>> queryAppCalculationOperations( int app, String month) {
        return  appCmtariffMapper.queryAppCalculationOperations(app,month);
    }

    public boolean saveAppCalculationOperations(AppCalculationOperationsEntity aco) {
        int i = appCmtariffMapper.saveAppCalculationOperations(aco);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateAppCalculationOperations(Double channel, Double market, Double service, Double tariff, int app, String month) {
        int i = appCmtariffMapper.updateAppCalculationOperations(channel,market,month,service,tariff,app);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }




}
