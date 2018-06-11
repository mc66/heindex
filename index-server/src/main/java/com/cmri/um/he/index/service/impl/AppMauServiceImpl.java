package com.cmri.um.he.index.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.dao.AppMauDao;
import com.cmri.um.he.index.service.AppActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 应用月活查询的服务类实现
 *
 * @author zhuyin
 * Created on 2018/6/4
 */
@Service
public class AppMauServiceImpl implements AppActiveService {
    @Autowired
    private AppMauDao activeDao;

    @Override
    public PagingData<Map<String, Object>> find(String month, int page, int step) {
        return new PagingData<>(activeDao.count(month),
                page,
                step,
                activeDao.find(month, page, step)
        );
    }

    @Override
    public PagingData<Map<String, Object>> find(String month, String category, int page, int step) {
        return new PagingData<>(activeDao.countOfCategory(month, category),
                page,
                step,
                activeDao.findByCategory(month, category, page, step)
        );
    }

}
