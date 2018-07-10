package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.AppOrginalFeaturesDao;
import com.cmri.um.he.index.quality.dao.AppWeightQualityDao;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppOriginalFeaturesEntity;
import com.cmri.um.he.index.quality.entity.AppWeightQualityEntity;
import com.cmri.um.he.index.quality.service.AppOrginalFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author shihao
 * Created on 2018/6/24
 */
@Service
public class AppOrginalFeaturesServiceImpl implements AppOrginalFeaturesService {

     @Autowired
     private AppOrginalFeaturesDao appOrginalFeaturesDao;

    @Autowired
     private AppWeightQualityDao appWeightQualityDao;

    /**
     * 新增功能界面的原始数据
     * @param list
     * @return
     */
    @Override
    public boolean saveList(List<AppOriginalFeaturesEntity> list) {
        for (AppOriginalFeaturesEntity appOriginalFeaturesEntity : list) {
            appOriginalFeaturesEntity.setStatus(1);
            appOriginalFeaturesEntity.setAtime(new Date());
            appOrginalFeaturesDao.saveList(appOriginalFeaturesEntity);
        }

        return true;
    }

    /**
     * 处理添加同一app的数据
     * @param list
     * @return
     */
    @Override
    public AppCalculationQualityEntity getScore(List<AppOriginalFeaturesEntity> list){
        int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;
        for(int i=0;i<list.size();i++) {

            if (list.get(i).getDimensions().equals("功能")) {
                String degree = list.get(i).getDegree();
                switch (degree) {
                    case "低": count1++;
                        break;
                    case "中": count2++;
                        break;
                    case "高": count3++;
                        break;
                    default:
                        count1 = count2 = count3 = 0;
                }
            } else if(list.get(i).getDimensions().equals("界面")){
                String degree = list.get(i).getDegree();
                switch (degree){
                    case "低": count4++;
                        break;
                    case "中": count5++;
                        break;
                    case "高": count6++;
                        break;
                    default: count4=count5=count6=0;
                }
            }
        }
        int app=list.get(0).getApp();
        AppWeightQualityEntity  appWeightQualityEntity= appWeightQualityDao.findWegihtByApp(app);

        int feaScore=100-(appWeightQualityEntity.getWhigh()*count1+appWeightQualityEntity.getWmiddle()*count2+appWeightQualityEntity.getWlow()*count3);
        int viewScore=100-(appWeightQualityEntity.getWhigh()*count4+appWeightQualityEntity.getWmiddle()*count5+appWeightQualityEntity.getWlow()*count6);

        AppCalculationQualityEntity appCalculationQualityEntity=new AppCalculationQualityEntity();
        appCalculationQualityEntity.setFeatures((double) feaScore);
        appCalculationQualityEntity.setView((double) viewScore);
        appCalculationQualityEntity.setApp(app);

        return appCalculationQualityEntity;
    }

}
