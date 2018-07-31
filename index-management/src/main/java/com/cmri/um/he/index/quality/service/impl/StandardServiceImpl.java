package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.StandardDao;
import com.cmri.um.he.index.quality.dao.StandardQualityDao;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppQualityEntity;
import com.cmri.um.he.index.quality.service.StandardService;
import com.cmri.um.he.index.util.StandardDeviationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 *处理标准值的服务类实现
 *
 * @author yinjunjun
 * Created on 2018/6/25
 */
@Service
public class StandardServiceImpl implements StandardService {

    private final static DecimalFormat DF=new DecimalFormat("#,##0.00");

    @Autowired
    private StandardQualityDao dao;
    @Autowired
    private StandardDao standardDao;

    /**
     * 查询未处理的数据
     * */
    @Override
    public List<Map<String, Object>> unprocessedStandard(Integer category,String month) {
        return dao.unprocessedStandard(category,month);
    }

    /**
     * 计算  将计算值转换为标准值   存入app_quality表
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void standard(List<AppCalculationQualityEntity> appCalculationQualityEntities) {
        int size = appCalculationQualityEntities.size();
        double[] featureArr= new double[size];
        double[] viewsArr = new double[size];
        double[] delayArr = new double[size];
        double[] consumeArr = new double[size];
        double[] experienceArr = new double[size];

        int i = 0;
        for (AppCalculationQualityEntity appCalculationQualityEntity : appCalculationQualityEntities) {
            if (appCalculationQualityEntity.getSpecial() ==1 && appCalculationQualityEntities.size()>6){
                //功能
                double features = appCalculationQualityEntity.getFeatures();
                featureArr[i] = features;
                //界面
                double views = appCalculationQualityEntity.getView();
                viewsArr[i] = views;
                //延时
                double delay = appCalculationQualityEntity.getDelay();
                delayArr[i] = delay;
                //功耗
                double consume = appCalculationQualityEntity.getConsume();
                consumeArr[i] = consume;
                //使用体验
                double experience = appCalculationQualityEntity.getExperience();
                experienceArr[i] = experience;
            }
            i++;
        }

        List<AppCalculationQualityEntity> appCalculations = new ArrayList<>();
        appCalculations.addAll(appCalculationQualityEntities);
        List<AppQualityEntity> appQualityEntities = new ArrayList<>();
        Integer cat = appCalculations.get(0).getCategory();
        String mon = appCalculations.get(0).getMonth();
        for (AppCalculationQualityEntity appCalculation : appCalculations) {
            //app
            Integer app = appCalculation.getApp();
            //app类别
            Integer category = appCalculation.getCategory();
            //功能
            double features = appCalculation.getFeatures();
            double newFeatures = 80;
            if (StandardDeviationUtil.getStandardDeviation(featureArr) != 0) {
                newFeatures = 80 + 10 * ((features - StandardDeviationUtil.getAverage(featureArr)) / StandardDeviationUtil.getStandardDeviation(featureArr));
            }
            String format = DF.format(newFeatures);
            Double newFea = Double.valueOf(format);
            //界面
            double views = appCalculation.getView();
            double newVie = 80;
            if (StandardDeviationUtil.getStandardDeviation(viewsArr) != 0) {
                newVie = 80 + 10 * ((views - StandardDeviationUtil.getAverage(viewsArr)) / StandardDeviationUtil.getStandardDeviation(viewsArr));
            }
            String formatViews = DF.format(newVie);
            Double newView = Double.valueOf(formatViews);
            //延时
            double delay = appCalculation.getDelay();
            double newDela = 80;
            if (StandardDeviationUtil.getStandardDeviation(delayArr) != 0){
                newDela = 80+10*((delay-StandardDeviationUtil.getAverage(delayArr))/StandardDeviationUtil.getStandardDeviation(delayArr));
            }
            String formatDelay = DF.format(newDela);
            Double newDelay = Double.valueOf(formatDelay);
            //功耗
            double consume = appCalculation.getConsume();
            double newCo = 80+10*((consume-StandardDeviationUtil.getAverage(consumeArr))/StandardDeviationUtil.getStandardDeviation(consumeArr));
            String formatCon = DF.format(newCo);
            double newCon = Double.valueOf(formatCon);
            //使用体验
            double experience = appCalculation.getExperience();
            double newExperience = 80;
            if (StandardDeviationUtil.getStandardDeviation(experienceArr) != 0) {
                newExperience = 80 + 10 * ((experience - StandardDeviationUtil.getAverage(experienceArr)) / StandardDeviationUtil.getStandardDeviation(experienceArr));
            }
            String formatExperience = DF.format(newExperience);
            double newExperiences = Double.valueOf(formatExperience);
            //版本号
            String version = appCalculation.getVersion();
            //月份
            String month = appCalculation.getMonth();
            AppQualityEntity appQualityEntity = new AppQualityEntity(null,app,category,newFea,newView,newDelay,newCon,newExperiences,0.0,version,month,new Date());
            appQualityEntities.add(appQualityEntity);
        }
        standardDao.saveAll(appQualityEntities);
        dao.updateStatus(cat,mon);
    }
}
