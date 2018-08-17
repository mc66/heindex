package com.cmri.um.he.index.appview.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.appview.dao.AppOperationsDao;
import com.cmri.um.he.index.appview.service.AppOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询当前应用类所有应用的运营数据的服务类的实现
 *
 * @author machao
 * Created on 2018/6/13
 */
@Service
public class AppOperationsServiceImpl implements AppOperationsService {

    @Autowired
    AppOperationsDao appOperationsDao;

    @Override
    public PagingData<Map<String, Object>> getOperationsList(String month, String category, int page, int step) {
        return new PagingData<>(appOperationsDao.countOfCategory(month, category),
                page,
                step,
                appOperationsDao.getOperationsList(month, category, page, step)
        );
    }

    @Override
    public  List<Map<String, Object>> getOperationsData(int id, String month) {
        String  st = month.substring(0, 4);
        String sub = month.substring(4, 5);
        if(sub.equals("上")){
            int months = Integer.parseInt(st);
            months=months-1;
            String month2 = Integer.toString(months)+"上";
            List resultList= appOperationsDao.getOperationsData(id,month2,month);
            return resultList;
        }else if(sub.equals("下")) {
            int months = Integer.parseInt(st);
            months=months-1;
            String month2 = Integer.toString(months)+"下";
            List resultList= appOperationsDao.getOperationsData(id,month2,month);
            return resultList;
        }
       return  null;

    }

    @Override
    public List<Map<String, Object>> queryQualityContent(Integer id, String month) {
        return appOperationsDao.queryQualityContent(id,month);
    }

    @Override
    public List<Map<String, Object>> queryQualityChannel(Integer id, String month) {
        return appOperationsDao.queryQualityChannel(id,month);
    }

    @Override
    public List<Map<String, Object>> queryQualityMarket(Integer id, String month) {
        return appOperationsDao.queryQualityMarket(id,month);
    }

    @Override
    public List<Map<String, Object>> queryQualityTariff(Integer id, String month) {
        return appOperationsDao.queryQualityTariff(id,month);
    }

    @Override
    public List<Map<String, Object>> queryQualityServe(Integer id, String month) {
        return appOperationsDao.queryQualityServe(id,month);
    }

    @Override
    public List<Map<String, Object>> queryQualityExperience(Integer id, String month) {
        return appOperationsDao.queryQualityExperience(id,month);
    }
}
