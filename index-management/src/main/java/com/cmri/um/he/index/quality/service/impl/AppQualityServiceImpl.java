package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.AppQualityDao;
import com.cmri.um.he.index.quality.dao.AppWeightQualityDao;
import com.cmri.um.he.index.quality.entity.AppWeightQualityEntity;
import com.cmri.um.he.index.quality.service.AppQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * 查询未计算体验指数的所有应用，计算应用的品质总分
 * @author machao
 * Created on 2018/6/28
 */
@Service
public class AppQualityServiceImpl implements AppQualityService {

    @Autowired
    AppQualityDao appQualityDao;

    @Autowired
    AppWeightQualityDao appWeightQualityDao;

    private final static DecimalFormat DF=new DecimalFormat("#,##0.00");

    /**
     * 查询未计算的应用
     * @return
     */
    @Override
    public List<Map<String, Object>> quary() {

        return appQualityDao.quaryQualityList();
    }

    /**
     * 计算应用的品质得分并存入数据库
     * @param qualityEntityList
     * @return
     */
    @Override
    public boolean setQindex(List<Map<String, Object>> qualityEntityList) {
        double qindex=0;
        try {
            for (Map<String, Object> appQualityEntity : qualityEntityList) {

                AppWeightQualityEntity appWeightQualityEntity = appWeightQualityDao.getWeight((int)appQualityEntity.get("category"));
                double feature = appWeightQualityEntity.getWfeatures();
                double view = appWeightQualityEntity.getWview();
                double delay = appWeightQualityEntity.getWdelay();
                double consume = appWeightQualityEntity.getWconsume();
                double experience = appWeightQualityEntity.getWexperience();

                //APP品质总得分=Σ（各维度品质标准值*该维度权重）
                qindex = (double)appQualityEntity.get("features")*feature+(double)appQualityEntity.get("views")*view+(double)appQualityEntity.get("delay")*delay+(double)appQualityEntity.get("consume")*consume+(double)appQualityEntity.get("experience")*experience;;
                String qindex1 = DF.format(qindex);
                qindex = Double.valueOf(qindex1);
                int i = appQualityDao.setQindex((int)appQualityEntity.get("app"), (String) appQualityEntity.get("version"), (String) appQualityEntity.get("month"), qindex);

            }
            return true;
        }catch (Exception e){
            return false;
        }

    }

}
