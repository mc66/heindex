package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.AppSecondQualityDelayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 二级页面时延功耗折线图
 * @author lch
 * Created on 2018/07/02 16:28
 */
@Repository
public class AppSecondQualityDelayDao extends BaseDao{
    @Autowired
    private AppSecondQualityDelayMapper delayMapper;

    /**
     * 根据应用类别、测量指标、网络环境、月份来查询测量值
     * @param category
     * @param measuring
     * @param network
     * @param month
     * @return
     */
    public List<Map<String,Object>> findQualityMeasureBySome(Integer category,String measuring, String network, String month){
        return delayMapper.findQualityMeasureBySome(category,measuring,network,month);
    }
}
