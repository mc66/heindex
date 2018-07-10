package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.QualityMonthDao;
import com.cmri.um.he.index.quality.service.QualityMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询所有报告期数的服务类实现
 *
 * @author yinjunjun
 * Created on 2018/6/27
 */
@Service
public class QualityMonthServiceImpl implements QualityMonthService {
    @Autowired
    private QualityMonthDao appMonthDao;
    /**
     * 查询所有月份
     * */
    @Override
    public List<Map<String, Object>> findMonths() {
        return appMonthDao.findMonths();
    }
}
