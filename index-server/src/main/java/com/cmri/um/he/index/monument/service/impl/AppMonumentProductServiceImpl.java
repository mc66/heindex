package com.cmri.um.he.index.monument.service.impl;

import com.cmri.um.he.index.common.CalculateDaysByDate;
import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.monument.dao.AppMonumentProductDao;
import com.cmri.um.he.index.monument.entity.AppEmotionAnalyzeEntity;
import com.cmri.um.he.index.monument.service.AppMonumentProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认展示时获取查询时间通用方法
 * @author machao
 * Created on 2018/8/10
 */
@Service
public class AppMonumentProductServiceImpl implements AppMonumentProductService {

    @Autowired
    AppMonumentProductDao appMonumentProductDao;
    private Integer freqPositive;

    /**
     * 查询每日评论数量统计
     * @param app
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Map<String,Object>> quaryDayCommentStatistics(Integer app, String startTime, String endTime)  {
        //计算时间范围内的天数，小于三十按天查并返回值，大于三十按半月查
        String firstTime = startTime;
        String lastTime = endTime;
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            int days = CalculateDaysByDate.calculateDaysByDate(startTime, endTime);
            if (days<=30){
                for (int i=0;i<days;i++){
                    Map<String,Object> map = new HashMap<>(7);
                    String date = CalculateDaysByDate.getDate(Constants.DAY, i, startTime);
                    Map<String, Object> dayCommentAmount = appMonumentProductDao.quaryDayCommentAmount(app, date);
                    double freqPositive = (long)dayCommentAmount.get("freqPositive");
                    double freqNegativity = (long)dayCommentAmount.get("freqNegativity");
                    double freqNeutral = (long)dayCommentAmount.get("freqNeutral");
                    map.put("freqPositive",freqPositive);
                    map.put("freqNegativity",freqNegativity);
                    map.put("freqNeutral",freqNeutral);
                    map.put("petPositive",new BigDecimal(freqPositive/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                    map.put("petNegativity",new BigDecimal(freqNegativity/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                    map.put("petNeutral",new BigDecimal(freqNeutral/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                    map.put("month",date);
                    list.add(map);
                }
            }else {
                int count = CalculateDaysByDate.calculateCountByDate(startTime, endTime);
                for (int j=0;j<count;j++){
                    Map<String,Object> map = new HashMap<>(8);
                    AppEmotionAnalyzeEntity appEmotionAnalyzeEntity = new AppEmotionAnalyzeEntity();
                    startTime = CalculateDaysByDate.getDate(Constants.DAY, j*15, startTime);
                    endTime = CalculateDaysByDate.getDate(Constants.DAY, 14, startTime);
                    Map<String, Object> commentAmount = appMonumentProductDao.quaryCommentAmount(app, startTime,endTime);
                    double freqPositive = (long)commentAmount.get("freqPositive");
                    double freqNegativity = (long)commentAmount.get("freqNegativity");
                    double freqNeutral = (long)commentAmount.get("freqNeutral");
                    map.put("freqPositive",freqPositive);
                    map.put("freqNegativity",freqNegativity);
                    map.put("freqNeutral",freqNeutral);
                    map.put("petPositive",new BigDecimal(freqPositive/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                    map.put("petNegativity",new BigDecimal(freqNegativity/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                    map.put("petNeutral",new BigDecimal(freqNeutral/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                    map.put("startTime",startTime);
                    map.put("endTime",endTime);
                    list.add(map);
                }
                if(days%15!=0){
                    Map<String,Object> map = new HashMap<>(8);
                    startTime = CalculateDaysByDate.getDate(Constants.DAY, (int)count*15, firstTime);
                    Map<String, Object> commentAmount = appMonumentProductDao.quaryCommentAmount(app, startTime,lastTime);
                    double freqPositive = (long)commentAmount.get("freqPositive");
                    double freqNegativity = (long)commentAmount.get("freqNegativity");
                    double freqNeutral = (long)commentAmount.get("freqNeutral");
                    map.put("freqPositive",freqPositive);
                    map.put("freqNegativity",freqNegativity);
                    map.put("freqNeutral",freqNeutral);
                    map.put("petPositive",new BigDecimal(freqPositive/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                    map.put("petNegativity",new BigDecimal(freqNegativity/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                    map.put("petNeutral",new BigDecimal(freqNeutral/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                    map.put("startTime",startTime);
                    map.put("endTime",endTime);
                    list.add(map);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

