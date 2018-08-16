package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppConversionDao;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.entity.AppOperationsEntity;
import com.cmri.um.he.index.operations.service.AppConversionService;
import com.cmri.um.he.index.quality.entity.AppWeightQualityEntity;
import com.cmri.um.he.index.util.StandardDeviationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *处理标准值的服务类实现
 *
 * @author yinjunjun
 * Created on 2018/07/10
 */
@Service
public class AppConversionServiceImpl implements AppConversionService {
    private final static DecimalFormat DF=new DecimalFormat("#,##0.00");
    @Autowired
    private AppConversionDao dao;

    /**
     * 查询未处理的数据
     * */
    @Override
    public List<AppCalculationOperationsEntity> queryUnConversion(Integer category, String month) {
        return dao.queryUnConversion(category, month);
    }

    /**
     * 计算  将计算值转换为标准值   存入app_operations表
     * */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<AppCalculationOperationsEntity> appCalculationOperationsEntities) {
        int size = appCalculationOperationsEntities.size();
        double[] contentArr= new double[size];
        double[] channelArr = new double[size];
        double[] tariffArr = new double[size];
        double[] serviceArr = new double[size];
        double[] marketArr = new double[size];
        double[] experienceArr = new double[size];
        double oindex=0;

        int i = 0;
        for (AppCalculationOperationsEntity appCalculationOperationsEntity : appCalculationOperationsEntities) {
                //内容
                double content = appCalculationOperationsEntity.getContent();
                contentArr[i] = content;
                //渠道
                double channel = appCalculationOperationsEntity.getChannel();
                channelArr[i] = channel;
                //资费
                double tariff = appCalculationOperationsEntity.getTariff();
                tariffArr[i] = tariff;
                //服务
                double service = appCalculationOperationsEntity.getService();
                serviceArr[i] = service;
                //营销
                double market = appCalculationOperationsEntity.getMarket();
                marketArr[i] = market;
                //运营体验
                double experience = appCalculationOperationsEntity.getExperience();
                experienceArr[i] = experience;
            i++;
        }

        List<AppCalculationOperationsEntity> appCalculations = new ArrayList<>();
        appCalculations.addAll(appCalculationOperationsEntities);
        List<AppOperationsEntity> appOperationsEntities = new ArrayList<>();
        Integer cate = appCalculations.get(0).getCategory();
        String month = appCalculations.get(0).getMonth();
        for (AppCalculationOperationsEntity appCalculationOperationsEntity : appCalculations) {
            Integer app = appCalculationOperationsEntity.getApp();
            Integer category = appCalculationOperationsEntity.getCategory();

                double content = appCalculationOperationsEntity.getContent();
                double a = StandardDeviationUtil.getAverage(contentArr);
                double b = StandardDeviationUtil.getStandardDeviation(contentArr);
                double newContent = 80;
                if (b!=0){
                    newContent = 80 + 10 * ((content - a) / b);
                }
                String format = DF.format(newContent);
                double newCon = Double.valueOf(format);

                double newChannel = 80;
                double channel = appCalculationOperationsEntity.getChannel();
                if (StandardDeviationUtil.getStandardDeviation(channelArr)!=0){
                    newChannel = 80 + 10 * ((channel - StandardDeviationUtil.getAverage(channelArr)) / StandardDeviationUtil.getStandardDeviation(channelArr));
                }
                String forCha = DF.format(newChannel);
                double newCha = Double.valueOf(forCha);

                double tariff = appCalculationOperationsEntity.getTariff();
                double newTariff = 80;
                if (StandardDeviationUtil.getStandardDeviation(tariffArr)!=0) {
                    newTariff = 80 + 10 * ((tariff - StandardDeviationUtil.getAverage(tariffArr)) / StandardDeviationUtil.getStandardDeviation(tariffArr));
                }
                String forTar = DF.format(newTariff);
                double newTar = Double.valueOf(forTar);

                double service = appCalculationOperationsEntity.getService();
                double newService = 80;
                if (StandardDeviationUtil.getStandardDeviation(serviceArr)!=0) {
                    newService = 80 + 10 * ((service - StandardDeviationUtil.getAverage(serviceArr)) / StandardDeviationUtil.getStandardDeviation(serviceArr));
                }
                String forSer = DF.format(newService);
                double newSer = Double.valueOf(forSer);

                double market = appCalculationOperationsEntity.getMarket();
                double newMarket = 80;
                if (StandardDeviationUtil.getStandardDeviation(marketArr) != 0) {
                    newMarket = 80 + 10 * ((market - StandardDeviationUtil.getAverage(marketArr)) / StandardDeviationUtil.getStandardDeviation(marketArr));
                }
                String forMar = DF.format(newMarket);
                double newMar = Double.valueOf(forMar);

                double experience = appCalculationOperationsEntity.getExperience();
                double newExperience = 80;
                if (StandardDeviationUtil.getStandardDeviation(experienceArr) != 0){
                    newExperience = 80 + 10 * ((experience - StandardDeviationUtil.getAverage(experienceArr)) / StandardDeviationUtil.getStandardDeviation(experienceArr));
                }
                String forEx = DF.format(newExperience);
                double newEx = Double.valueOf(forEx);

            String version = appCalculationOperationsEntity.getVersion();

            //根据应用类别查询各维度权重值
            AppWeightQualityEntity appWeightQualityEntity = dao.getWeight(category);
            double wcontent = appWeightQualityEntity.getWcontent();
            double wchannel = appWeightQualityEntity.getWchannel();
            double wmarket = appWeightQualityEntity.getWmarket();
            double wexpenses = appWeightQualityEntity.getWexpenses();
            double wservice = appWeightQualityEntity.getWservice();
            double wexperience = appWeightQualityEntity.getWexperienceOperation();

            //APP运营总得分=Σ（各维度品质标准值*该维度权重）
            oindex = newCon*wcontent+newCha*wchannel+newMar*wmarket+newEx*wexpenses+newSer*wservice+newExperience*wexperience;
            String oindex1 = DF.format(oindex);
            oindex = Double.valueOf(oindex1);

            AppOperationsEntity appOperationsEntity = new AppOperationsEntity(null,app,category,newCon,newCha,newTar,newSer,newMar,newEx,oindex,version,month,new Date());
            appOperationsEntities.add(appOperationsEntity);
        }
        dao.saveAll(appOperationsEntities);
        dao.changeStatus(cate,month);
    }

    @Override
    public List<Map<String, Object>> findMonths() {
        return dao.findMonths();
    }
}
