package com.cmri.um.he.index.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.dao.AppOperationsDao;
import com.cmri.um.he.index.service.AppOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
