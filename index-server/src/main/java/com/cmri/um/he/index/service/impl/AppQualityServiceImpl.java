package com.cmri.um.he.index.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.dao.AppQualityDao;
import com.cmri.um.he.index.service.AppQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
* 查询某类应用品质指数
* @author shihao
* Created on 2018/6/13
*/
@Service
public class AppQualityServiceImpl implements AppQualityService {

    @Autowired
    private AppQualityDao appQualityDao;


    @Override
    public PagingData<Map<String, Object>> find(String month, String category, int page, int step) {
        return new PagingData<>(appQualityDao.getCount(month, category),
                page,
                step,
                appQualityDao.getQualityList(month, category, page, step)
        );
    }

    @Override
    public List<Map<String, Object>> getQualityData(int id, String month) {
        int months = Integer.parseInt(month);
        months=months-2;
        String month2 = Integer.toString(months);
        List resultList= appQualityDao.getQualityData(id,month2,month);
        return resultList;
    }

    @Override
    public List<Map<String, Object>> queryQualityFeatures(Integer app, String month) {
        return appQualityDao.queryQualityFeatures(app,month);
    }

    @Override
    public List<Map<String, Object>> queryQualityViews(Integer app, String month) {
        return appQualityDao.queryQualityViews(app,month);
    }

    @Override
    public List<Map<String, Object>> queryQualityDelay(Integer app, String month) {
        return appQualityDao.queryQualityDelay(app,month);
    }

    @Override
    public List<Map<String, Object>> queryQualityConsume(Integer app, String month) {
        return appQualityDao.queryQualityConsume(app,month);
    }

    @Override
    public List<Map<String, Object>> queryQualityExperience(Integer app, String month) {
        return appQualityDao.queryQualityExperience(app,month);
    }

    @Override
    public List<Map<String,Object>> getAppLogo(int appId) {
        return appQualityDao.getAppLogo(appId);
    }
}
