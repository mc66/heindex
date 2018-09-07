package com.cmri.um.he.index.mouth.serivice.impl;

import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.mouth.dao.AppEmotionAnalyzeDao;
import com.cmri.um.he.index.mouth.entity.AppEmotionAnalyzeEntity;
import com.cmri.um.he.index.mouth.entity.AppEmotionParameterEntity;
import com.cmri.um.he.index.mouth.serivice.AppEmotionAnalyzeService;
import com.cmri.um.he.index.util.StandardDeviationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 	口碑指数原始数据录入
 * @author shihao
 * Created on 2018/8/14
 */
@Service
public class AppEmotionAnalyzeServiceImpl implements AppEmotionAnalyzeService {

    @Autowired
    private AppEmotionAnalyzeDao appEmotionAnalyzeDao;

    @Override
    public Boolean saveMouth(List<AppEmotionAnalyzeEntity> list) {

        for (AppEmotionAnalyzeEntity appEmotionAnalyzeEntity : list) {
            appEmotionAnalyzeEntity.setAtime(new Date());
            appEmotionAnalyzeDao.saveMouth(appEmotionAnalyzeEntity);
        }
        return  true;
    }
    @Override
    public Boolean  getEmotionScore(){
        int flag=0;
        //获得所有分类
        List<Map<String,Object>> list=appEmotionAnalyzeDao.getCategory();
        //获得某类下的所有app
        for (Map<String, Object> stringObjectMap : list) {
            int category = (int)stringObjectMap.get("category");
            List<Map<String,Object>> list1 =appEmotionAnalyzeDao.getAppByCategory(category);
            //获得某app的所有数据的月份
            for (Map<String, Object> objectMap : list1) {
                int app = (int)objectMap.get("app");
                List<Map<String,Object>> list2=appEmotionAnalyzeDao.getMonth(category,app);
                for (Map<String, Object> map : list2) {
                    String month = (String)map.get("month");
                    try {
                        String defaultTimes = DefaultTime.getDefaultTimes(Constants.MONTH, 5, month);
                        defaultTimes=defaultTimes+"01";
                        String months=month+"31";
                        //计算该app的所有月份的所有评论数量
                        int freq_sum =appEmotionAnalyzeDao.getFavorableRate(category,app,months,defaultTimes);
                        //计算该app的所有月份的所有好评论数量
                        int freq_positive =appEmotionAnalyzeDao.getFavorableRate1(category,app,months,defaultTimes);
                        //计算该app的所有月份的所有中评论数量
                        int freq_negativity =appEmotionAnalyzeDao.getFavorableRate2(category,app,months,defaultTimes);
                        //计算该app的所有月份的所有差评论数量
                        int freq_neutral =appEmotionAnalyzeDao.getFavorableRate3(category,app,months,defaultTimes);
                        double freq_sums=(double) freq_sum;
                        double pet_positive=new BigDecimal((float)freq_positive/freq_sums*100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        String pet_positives = Double.toString(pet_positive);
                        pet_positives=pet_positives+"%";
                        double pet_negativity=new BigDecimal((float)freq_negativity/freq_sums*100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        String pet_negativitys =Double.toString(pet_negativity);
                        pet_negativitys=pet_negativitys+"%";
                        double pet_neutral=new BigDecimal((float)freq_neutral/freq_sums*100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        String pet_neutrals =Double.toString(pet_neutral);
                        pet_neutrals=pet_neutrals+"%";
                        //净好率
                        double favorable_rate=new BigDecimal((float)pet_positive -pet_negativity).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        String favorable_rates=Double.toString(favorable_rate);
                        favorable_rates=favorable_rates+"%";

                        //保存净好评率及正中负的分数
                        AppEmotionParameterEntity entity=new AppEmotionParameterEntity();
                        entity.setCategory(category);
                        entity.setApp(app);
                        entity.setMonth(month);
                        entity.setFavorableRate(favorable_rates);
                        entity.setPetPositive(pet_positives);
                        entity.setPetNegativity(pet_negativitys);
                        entity.setPetNeutral(pet_neutrals);
                        entity.setFreqSum(freq_sum);
                        entity.setFreqPositive(freq_positive);
                        entity.setFreqNegativity(freq_negativity);
                        entity.setFreqNeutral(freq_neutral);
                        entity.setAtime(new Date());
                        //查询该app在该月份是否有数据
                        AppEmotionParameterEntity entitys=appEmotionAnalyzeDao.selectByApp(category,app,month);
                        if(entitys==null){
                            flag=appEmotionAnalyzeDao.saveFavorableRate(entity);
                        }else{
                            flag=appEmotionAnalyzeDao.updateFavorableRate(entity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //净好评率Z/T转化及月活数的Z/T转化
        //获得类下所有月份(app_emotion_parameter)
        for (Map<String, Object> obMap : list) {
            int category = (int)obMap.get("category");
            List<Map<String,Object>> list3 =appEmotionAnalyzeDao.getMonthByParameter(category);
            for (Map<String, Object> stringObjectMap : list3) {
                String month = (String)stringObjectMap.get("month");
                //获得该类下的某一月份下的所有app的净好评率分数
                List<Map<String,Object>> list4=  appEmotionAnalyzeDao.getFavorableRates(category,month);
                List<Double> list5 =new ArrayList();
                for (Map<String, Object> objectMap : list4) {
                        String  favorable_rate = (String)objectMap.get("favorable_rate");
                        Double favorableRate = Double.parseDouble(favorable_rate.split("%")[0]);
                        list5.add(favorableRate);
                }
                Double[] doubles =list5.toArray(new Double[list5.size()]);
                double average = StandardDeviationUtil.getAverage(doubles);
                Double standardDeviation = StandardDeviationUtil.getStandardDeviation(doubles);
                //计算净好评率的Z分,T分
                for (Map<String, Object> objectMap : list4) {
                    int app = (int)objectMap.get("app");
                    String  favorable_rate = (String)objectMap.get("favorable_rate");
                    Double favorableRate = Double.parseDouble(favorable_rate.split("%")[0]);
                    //Z分
                    double favorableRateZ=0.0;
                    if(standardDeviation==0){
                        favorableRateZ=new BigDecimal((float)(favorableRate - average)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    }else {
                        favorableRateZ = new BigDecimal((float) (favorableRate - average) / standardDeviation).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    //T分
                    double sd=10;
                    double avg=80;
                    double favorableRateT=favorableRateZ*sd + avg;

                    //保存净好评率的Z分和T分
                    flag=appEmotionAnalyzeDao.updateRate( category,app, month, favorableRateZ, favorableRateT);
                }
                //查询该类下的某一月份下的所有app的月活数
                List<Map<String,Object>> list6=appEmotionAnalyzeDao.getMau(category,month);
                List<Double> list7 =new ArrayList();
                for (Map<String, Object> objectMap : list6) {
                    double mau = (double)objectMap.get("monthly_active");
                    list7.add(mau);
                }
                if(list6.size()==0){

                }else {
                    //获得该月下的app的平均值和标准差
                    Double[] dou =list7.toArray(new Double[list7.size()]);
                    double mau_avg = StandardDeviationUtil.getAverage(dou);
                    Double mau_dev = StandardDeviationUtil.getStandardDeviation(dou);
                    //计算月活数的Z分,T分
                    for (Map<String, Object> objectMap : list6) {
                        double mau = (double)objectMap.get("monthly_active");
                        int app= (int)objectMap.get("app");
                        //Z分
                        double mauZ=0.0;
                        if(mau_dev==0){
                            mauZ=new BigDecimal((float)(mau - mau_avg)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        }else {
                            mauZ = new BigDecimal((float) (mau - mau_avg)/mau_dev).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                        //T分
                        double sd=10;
                        double avg=80;
                        double mauT=mauZ*sd + avg;
                        if(mauT>100){
                            mauT=100;
                        }
                        //保存在该月份下的app的月活数的Z/T分
                        flag=appEmotionAnalyzeDao.updateMau( category,app, month,mauZ, mauT);
                    }
                }
                //获得该类下某月份的情感得分
                List<Map<String,Object>> list8=appEmotionAnalyzeDao.getEmotionScore(category,month);
                List<Double> list9 =new ArrayList();
                for (Map<String, Object> objectMap : list8) {
                    double mauT =(double)objectMap.get("mau_t");
                    double favorableRateT =(double)objectMap.get("favorable_rate_t");
                    //情感得分
                    double emotionRateOriginal =mauT*favorableRateT;
                    list9.add(emotionRateOriginal);
                }
                //情感得分的平均值/标准查
                Double[] ble =list9.toArray(new Double[list9.size()]);
                double emotion_avg = StandardDeviationUtil.getAverage(ble);
                Double emotion_dev = StandardDeviationUtil.getStandardDeviation(ble);
                //情感得分的Z/T转化
                for (Map<String, Object> objectMap : list8) {
                    int app= (int)objectMap.get("app");
                    double mauT =(double)objectMap.get("mau_t");
                    double favorableRateT =(double)objectMap.get("favorable_rate_t");
                    //情感得分
                    double emotionRateOriginal =mauT*favorableRateT;
                    //Z分
                    double emotionRateZ=0.0;
                    if(emotion_dev==0){
                        emotionRateZ=new BigDecimal((float)(emotionRateOriginal-emotion_avg)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    }else {
                        emotionRateZ = new BigDecimal((float) (emotionRateOriginal-emotion_avg)/emotion_dev).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    //T分
                    double sd=10;
                    double avg=80;
                    double emotionScore=emotionRateZ*sd + avg;
                    if(emotionScore>100){
                        emotionScore=100;
                    }
                    //保存情感得分
                    flag=appEmotionAnalyzeDao.updateEmotionScore(category,app,month,emotionRateOriginal,emotionRateZ,emotionScore);
                }
            }
        }
        if (flag>0){
            return true;
        }else {
            return false;
        }

    }

}
