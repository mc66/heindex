package com.cmri.um.he.index.operations.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.operations.dao.AppCmtariffDao;
import com.cmri.um.he.index.operations.dao.AppDimensionsWeightDao;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.entity.AppOriginalOperationsEntity;
import com.cmri.um.he.index.operations.service.AppCmtariffService;
import com.cmri.um.he.index.operations.service.AppDimensionsWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 维度权重管理
 * @author machao
 * Created on 2018/7/17
 */
@Service
public class AppDimensionsWeightImpl implements AppDimensionsWeightService {

    @Autowired
    AppDimensionsWeightDao appDimensionsWeightDao;

    @Override
    public PagingData<Map<String, Object>> getAllDimensions(int page, int step) {
        return new PagingData<>(appDimensionsWeightDao.count(),
                page,
                step,
                appDimensionsWeightDao.getAllDimensions(page, step)
        );
    }

    @Override
    public PagingData<Map<String, Object>> getAllDimensionsByAspect(int aspect, int page, int step) {
        return new PagingData<>(appDimensionsWeightDao.countOfAspect(aspect),
                page,
                step,
                appDimensionsWeightDao.getAllDimensionsByAspect(aspect, page, step)
        );
    }

    @Override
    public void updateWeight(int id, double weight) {
        appDimensionsWeightDao.updateWeight(id,weight);
    }
}
