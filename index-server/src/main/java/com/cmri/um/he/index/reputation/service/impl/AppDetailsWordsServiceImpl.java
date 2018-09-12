package com.cmri.um.he.index.reputation.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.common.CalculateDaysByDate;
import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.reputation.dao.AppDetailsWordsDao;
import com.cmri.um.he.index.reputation.entity.AppEmotionAnalyzeEntity;
import com.cmri.um.he.index.reputation.service.AppDetailsWordsService;
import com.cmri.um.he.index.receivable.CommentParticularsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 默认展示时获取查询时间
 * @author limin
 * Created on 2018/8/16
 */
@Service
public class AppDetailsWordsServiceImpl implements AppDetailsWordsService {

    @Autowired
    private AppDetailsWordsDao dao;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *  查询每日评论数量统计
     * @param comment
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Map<String, Object>> quaryquantitativeLi(String comment, String startTime, String endTime) {

        List<Map<String,Object>> list = new ArrayList<>();
            //计算时间范围内的天数，小于三十按天查并返回值，大于三十按半月查
            String firstTime = startTime;
            String lastTime = endTime;
            try {
                int days = CalculateDaysByDate.calculateDaysByDate(startTime, endTime);
                if (days<=30){
                    for (int i=0;i<days;i++){
                        Map<String,Object> map = new HashMap<>(7);
                        String date = CalculateDaysByDate.getDate(Constants.DAY, i, startTime);
                        Map<String, Object> dayCommentAmount = dao.quaryDayqQuantitative(comment, date);
                        double freqPositive = (long)dayCommentAmount.get("freqPositive");
                        double freqNegativity = (long)dayCommentAmount.get("freqNegativity");
                        double freqNeutral = (long)dayCommentAmount.get("freqNeutral");
                        double sum = freqPositive + freqNegativity + freqNeutral;
                        if (sum==0) {
                            map.put("freqPositive", 0);
                            map.put("freqNegativity", 0);
                            map.put("freqNeutral", 0);
                            map.put("total", sum);
                            map.put("petPositive", "0.00%");
                            map.put("petNegativity", "0.00%");
                            map.put("petNeutral", "0.00%");
                            map.put("month", date);
                        }else {
                            map.put("freqPositive",freqPositive);
                            map.put("freqNegativity",freqNegativity);
                            map.put("freqNeutral",freqNeutral);
                            map.put("total",sum);
                            map.put("petPositive",new BigDecimal(freqPositive/sum*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                            map.put("petNegativity",new BigDecimal(freqNegativity/sum*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                            map.put("petNeutral",new BigDecimal(freqNeutral/sum*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                            map.put("month",date);
                            list.add(map);
                        }
                    }
                }else {
                    int count = CalculateDaysByDate.calculateCountByDate(startTime, endTime);
                    for (int j=0;j<count;j++){
                        Map<String,Object> map = new HashMap<>(8);
                        AppEmotionAnalyzeEntity appEmotionAnalyzeEntity = new AppEmotionAnalyzeEntity();
                        startTime = CalculateDaysByDate.getDate(Constants.DAY, j*15, firstTime);
                        endTime = CalculateDaysByDate.getDate(Constants.DAY, 14, startTime);
                        Map<String, Object> commentAmount = dao.quaryquantitative(comment, startTime,endTime);
                        double freqPositive = (long)commentAmount.get("freqPositive");
                        double freqNegativity = (long)commentAmount.get("freqNegativity");
                        double freqNeutral = (long)commentAmount.get("freqNeutral");
                        map.put("freqPositive",freqPositive);
                        map.put("freqNegativity",freqNegativity);
                        map.put("freqNeutral",freqNeutral);
                        map.put("total",freqPositive+freqNegativity+freqNeutral);
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
                        Map<String, Object> commentAmount = dao.quaryquantitative(comment, startTime,lastTime);
                        double freqPositive = (long)commentAmount.get("freqPositive");
                        double freqNegativity = (long)commentAmount.get("freqNegativity");
                        double freqNeutral = (long)commentAmount.get("freqNeutral");
                        map.put("freqPositive",freqPositive);
                        map.put("freqNegativity",freqNegativity);
                        map.put("freqNeutral",freqNeutral);
                        map.put("total",freqPositive+freqNegativity+freqNeutral);
                        map.put("petPositive",new BigDecimal(freqPositive/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                        map.put("petNegativity",new BigDecimal(freqNegativity/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                        map.put("petNeutral",new BigDecimal(freqNeutral/(freqPositive+freqNegativity+freqNeutral)*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
                        map.put("startTime",startTime);
                        map.put("endTime",lastTime);
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

    @Override
    public PagingData<Map<String, Object>> quaryMonthlySentiment(CommentParticularsVO commentParticularsVO) {

        return  new PagingData<>(dao.count(commentParticularsVO),
                commentParticularsVO.getPage(),
                commentParticularsVO.getStep(),
                dao.quaryCommentParticulars(commentParticularsVO,commentParticularsVO.getPage(),commentParticularsVO.getStep())
        );
    }

    @Override
    public List<String> quaryHotWords(String startTime, String endTime) {
        List<String> list = dao.quaryHotWords(startTime,endTime);
        return list;
    }

    @Override
    public List<String> quarySourceComment(String startTime, String endTime) {
        List<String> list = dao.quarySourceComment(startTime,endTime);
        return list;
    }
}
