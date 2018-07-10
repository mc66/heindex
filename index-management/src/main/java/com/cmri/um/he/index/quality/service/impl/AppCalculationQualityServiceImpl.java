package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.AppCalculationQualityDao;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.service.AppCalculationQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 保存计算后的功能界面得分
 * author shihao
 * Created on 2018/6/27
 */
@Service
public class AppCalculationQualityServiceImpl implements AppCalculationQualityService {

    @Autowired
    private AppCalculationQualityDao appCalculationQualityDao;

    @Override
    public int update(AppCalculationQualityEntity appCalculationQualityEntity) {
       return  appCalculationQualityDao.update(appCalculationQualityEntity);

    }
}
