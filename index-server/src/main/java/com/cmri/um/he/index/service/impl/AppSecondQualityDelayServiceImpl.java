package com.cmri.um.he.index.service.impl;

import com.cmri.um.he.index.dao.AppSecondQualityDelayDao;
import com.cmri.um.he.index.service.AppSecondQualityDelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 二级页面时延功耗折线图
 * @author lch
 * Created on 2018/07/03 09:29
 */
@Service
public class AppSecondQualityDelayServiceImpl implements AppSecondQualityDelayService{
    @Autowired
    private AppSecondQualityDelayDao delayDao;

    /**
     * 根据应用类别、测量指标、网络环境、月份来查询测量值
     * @param category
     * @param measuring
     * @param network
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Object>> findQualityMeasureBySome(Integer category,String measuring, String network, String month) {
        return delayDao.findQualityMeasureBySome(category,measuring,network,month);
    }
}
