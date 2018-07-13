package com.cmri.um.he.index.appview.service;

import java.util.List;
import java.util.Map;

/**
 * 二级页面时延功耗折线图
 * @author lch
 * Created on 2018/07/02 16:33
 */
public interface AppSecondQualityDelayService {

    /**
     * 根据应用类别、测量指标、网络环境、月份来查询测量值
     * @param id
     * @param measuring
     * @param network
     * @param month
     * @return
     */
    List<Map<String,Object>> findQualityMeasureBySome(Integer id,String measuring, String network, String month);
}
