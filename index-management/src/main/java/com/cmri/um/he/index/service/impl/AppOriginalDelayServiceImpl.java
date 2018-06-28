package com.cmri.um.he.index.service.impl;

import com.cmri.um.he.index.dao.AppOriginalDelayDao;
import com.cmri.um.he.index.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.entity.AppOriginalDelayEntity;
import com.cmri.um.he.index.service.AppOriginalDelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 品质得分中时延、功耗
 * @author lch
 * Created on 2018/06/27 09:55
 */
@Service
public class AppOriginalDelayServiceImpl implements AppOriginalDelayService {
    @Autowired
    private AppOriginalDelayDao delayDao;

    @Override
    public List<Map<String, Object>> findAppByCategory(Integer category) {
        return delayDao.findAppByCategory(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAppOriginalDelayList(List<AppOriginalDelayEntity> list) {
        Date date=new Date();
        for (AppOriginalDelayEntity entity:list) {
            entity.setAtime(date);
            //1--正常 0--已删除
            entity.setStatus(1);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean dealAppOriginalDelayList(List<AppOriginalDelayEntity> list) {
        //启动时延
        AppOriginalDelayEntity entity1=list.get(0);
        AppOriginalDelayEntity entity2=list.get(1);
        AppOriginalDelayEntity entity3=list.get(2);
        double S1 = dealEntityList(entity1, entity2, entity3);
        //浏览时延
        AppOriginalDelayEntity entity4=list.get(3);
        AppOriginalDelayEntity entity5=list.get(4);
        AppOriginalDelayEntity entity6=list.get(5);
        double S2 = dealEntityList(entity4, entity5, entity6);
        //上传速率
        AppOriginalDelayEntity entity7=list.get(6);
        AppOriginalDelayEntity entity8=list.get(7);
        AppOriginalDelayEntity entity9=list.get(8);
        double S3 = dealEntityList(entity7, entity8, entity9);
        //下载速率
        AppOriginalDelayEntity entity10=list.get(9);
        AppOriginalDelayEntity entity11=list.get(10);
        AppOriginalDelayEntity entity12=list.get(11);
        double S4 = dealEntityList(entity10, entity11, entity12);
        //延时计算值
        double delay=(S1+S2+S3+S4)/4;
        //CPU耗电量
        AppOriginalDelayEntity entity13=list.get(12);
        AppOriginalDelayEntity entity14=list.get(13);
        AppOriginalDelayEntity entity15=list.get(14);
        double S5 = dealEntityList(entity13, entity14, entity15);
        //CPU占用峰值
        AppOriginalDelayEntity entity16=list.get(15);
        AppOriginalDelayEntity entity17=list.get(16);
        AppOriginalDelayEntity entity18=list.get(17);
        double S6 = dealEntityList(entity16, entity17, entity18);
        //内存占用峰值
        AppOriginalDelayEntity entity19=list.get(18);
        AppOriginalDelayEntity entity20=list.get(19);
        AppOriginalDelayEntity entity21=list.get(20);
        double S7 = dealEntityList(entity19, entity20, entity21);
        //功耗计算值
        double consume=(S5+S6+S7)/3;
        //补全数据
        AppCalculationQualityEntity entity=new AppCalculationQualityEntity(0,entity1.getApp(),entity1.getCategory(),0.0,0.0,delay,consume,0.0,entity1.getVersion(),1,entity1.getMonth(),new Date());
        int i = delayDao.saveDelay(entity);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    private double dealEntityList(AppOriginalDelayEntity entity1,AppOriginalDelayEntity entity2,AppOriginalDelayEntity entity3){
        double S1 = dealEntity(entity1);
        double S2 = dealEntity(entity2);
        double S3 = dealEntity(entity3);
        double S=(S1+S2+S3)/3;
        return S;
    }

    private double dealEntity(AppOriginalDelayEntity entity){
        //测量值
        double X=entity.getMeasure();
        //挑战值
        double A=entity.getChallenge();
        //达标值
        double B=entity.getStandard();
        double Sx=(B-X)/(B-A)*40+60;
        return Sx;
    }
}
