package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppCmtariffDao;
import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;
import com.cmri.um.he.index.operations.service.AppCmtariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public boolean saveAppOriginalContentEntity(List<AppOriginalContentEntity> list) {
        Date date=new Date();
        for (AppOriginalContentEntity entity :list) {
            entity.setAtime(date);
            appCmtariffDao.saveAppOriginalContentEntity(entity);
        }
        return true;
    }

    @Override
    public boolean dealAppOriginalContentEntity(List<AppOriginalContentEntity> list) {

        return false;
    }
}
