package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppOperationExperienceDao;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.mapper.AppOperationExperienceMapper;
import com.cmri.um.he.index.operations.service.AppOperationExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 处理app运营体验得分
 * @author machao
 * Created on 2018/7/11
 */
@Service
public class AppOperationExperienceServiceImpl implements AppOperationExperienceService {

    @Autowired
    AppOperationExperienceDao appOperationExperienceDao ;

    @Override
    public AppCalculationOperationsEntity find(AppCalculationOperationsEntity appCalculationOperationsEntity) {
        String month = appCalculationOperationsEntity.getMonth();
        Integer app = appCalculationOperationsEntity.getApp();
        String version = appCalculationOperationsEntity.getVersion();
        return appOperationExperienceDao.find(month,app,version);
    }

    @Override
    public void update(int id,double experience) {
        appOperationExperienceDao.update(id,experience);
    }


    @Override
    public void add(AppCalculationOperationsEntity appCalculationOperationsEntity) {
        Date atime = new Date();
        AppCalculationOperationsEntity acoe = new AppCalculationOperationsEntity(0, appCalculationOperationsEntity.getMonth(), appCalculationOperationsEntity.getApp(), appCalculationOperationsEntity.getCategory(), appCalculationOperationsEntity.getVersion(), 0.0, 0.0, 0.0, 0.0, 0.0, appCalculationOperationsEntity.getExperience(), 0, atime);
        appOperationExperienceDao.add(acoe);
    }
}
