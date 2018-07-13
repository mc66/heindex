package com.cmri.um.he.index.appview.service.impl;

import com.cmri.um.he.index.appview.dao.AppMonthDao;
import com.cmri.um.he.index.appview.service.AppMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 查询所有报告期数的服务类实现
 *
 * @author yinjunjun
 * Created on 2018/6/15
 */
@Service
public class AppMonthServiceImpl implements AppMonthService {
    @Autowired
    private AppMonthDao appMonthDao;
    @Override
    public List<Map<String, Object>> findMonths() {
        return appMonthDao.findMonths();
    }
}
