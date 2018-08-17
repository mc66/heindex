package com.cmri.um.he.index.mouth.dao;

import com.cmri.um.he.index.mouth.entity.AppEmotionAnalyzeEntity;
import com.cmri.um.he.index.mouth.entity.AppEmotionParameterEntity;
import com.cmri.um.he.index.mouth.mapper.AppEmotionAnalyzeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 	口碑指数原始数据录入
 * @author shihao
 * Created on 2018/8/14
 */
@Repository
public class AppEmotionAnalyzeDao {
    @Autowired
    private AppEmotionAnalyzeMapper mapper;

    /**
     * 添加某app的评论数据
     * @param entity
     * @return
     */
    public Boolean saveMouth(AppEmotionAnalyzeEntity entity) {
        int i= mapper.saveMouth(entity);
        if (i>0) {
            return true;
        }else {
            return  false;
        }
    }
    /**
     * 获得所有分类
     * @return
     */
    public List<Map<String,Object>> getCategory(){
          return mapper.getCategory();
    };
    /**
     * 获得某类下的所有app
     * @param category
     * @return
     */
    public List<Map<String,Object>> getAppByCategory(int category){
        return mapper.getAppByCategory(category);
    };


    /**
     *  获得某app的所有数据的月份
     * @param category
     * @param app
     * @return
     */
    public List<Map<String,Object>> getMonth(int category,int app){
        return mapper.getMonth(category,app);
    };

    /**
     * 查询app在某月的正中负的评论数量
     * @param category
     * @param app
     * @param month
     * @param defaultTimes
     * @return
     */
    public int getFavorableRate(int category,int app, String month,String defaultTimes){
        return  mapper.getFavorableRate(category,app,month,defaultTimes);
    };
    public int getFavorableRate1(int category,int app, String month,String defaultTimes){
        return  mapper.getFavorableRate1(category,app,month,defaultTimes);
    };
    public int getFavorableRate2(int category,int app, String month,String defaultTimes){
        return  mapper.getFavorableRate2(category,app,month,defaultTimes);
    };
    public int getFavorableRate3(int category,int app, String month,String defaultTimes){
        return  mapper.getFavorableRate3(category,app,month,defaultTimes);
    };

    /**
     * 查询该app在该月份是否有数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    public AppEmotionParameterEntity selectByApp(int category,int app, String month){
        return  mapper.selectByApp(category,app,month);
    }
    /**
     * 保存净好评率及正中负的个数
     * @param entity
     * @return
     */
    public int saveFavorableRate(AppEmotionParameterEntity entity){
        return mapper.saveFavorableRate(entity);
    }
    public int updateFavorableRate(AppEmotionParameterEntity entity){
        return mapper.updateFavorableRate(entity);
    }


    /**
     * 获得类下所有月份(app_emotion_parameter)
     * @param category
     * @return
     */
    public List<Map<String,Object>> getMonthByParameter(int category){
        return mapper.getMonthByParameter(category);
    }

    /**
     * 获得该类下的某一月份下的所有app的净好评率分数
     * @param category
     * @param month
     * @return
     */
    public List<Map<String,Object>> getFavorableRates(int category,String month){
        return mapper.getFavorableRates(category,month);
    }
    /**
     * 保存净好评率的Z分和T分
     * @param category
     * @param app
     * @param month
     * @param favorableRateZ
     * @param favorableRateT
     * @return
     */
    public int updateRate(int category,int app,String month,double favorableRateZ,double favorableRateT){
        return mapper.updateRate( category,app, month, favorableRateZ, favorableRateT);
    }
    /**
     * 查询该类下的某一月份下的所有app的月活数
     * @param category
     * @param month
     * @return
     */
    public List<Map<String,Object>> getMau(int category,String month){

        return  mapper.getMau(category,month);
    }
    /**
     * 保存在该月份下的app的月活数的Z/T分
     * @param category
     * @param app
     * @param month
     * @param mauZ
     * @param mauT
     * @return
     */
    public int updateMau(int category,int app,String month,double mauZ,double mauT){
        return mapper.updateMau( category,app,month, mauZ, mauT);
    }

    /**
     * 获得净好评率和月活得分
     * @param category
     * @param month
     * @return
     */
    public List<Map<String,Object>> getEmotionScore(int category,String month){
        return mapper.getEmotionScore(category,month);
    }

    /**
     * 保存情感得分
     * @param category
     * @param app
     * @param month
     * @param emotionRateOriginal
     * @param emotionRateZ
     * @param emotionScore
     * @return
     */
    public int updateEmotionScore(int category,int app,String month,double emotionRateOriginal,double emotionRateZ,double emotionScore){
        return  mapper.updateEmotionScore(category,app,month,emotionRateOriginal,emotionRateZ,emotionScore);
    }
}
