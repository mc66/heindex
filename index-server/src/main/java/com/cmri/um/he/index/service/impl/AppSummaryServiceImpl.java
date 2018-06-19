package com.cmri.um.he.index.service.impl;

import com.cmri.um.he.index.dao.AppSummaryDao;
import com.cmri.um.he.index.service.AppSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 某类应用查询的服务类实现
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@Service
public class AppSummaryServiceImpl implements AppSummaryService {

    @Autowired
    private AppSummaryDao appSummaryDao;

    @Override
    public List<Map<String, Object>> findQuality(String month, Integer category) {
        return appSummaryDao.findQuality(month, category);
    }

    @Override
    public List<Map<String, Object>> findOperation(String month, Integer category) {
        return appSummaryDao.findOperation(month, category);
    }
}
