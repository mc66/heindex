package com.cmri.um.he.index.monument.service.impl;

import com.cmri.um.he.index.monument.dao.AppBereavementDao;
import com.cmri.um.he.index.monument.service.AppBereavementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 情感指数变化趋势的服务类的服务类实现
 *
 * @author yinjunjun
 * Created on 2018/6/10
 */
@Service
public class AppBereavementServiceImpl implements AppBereavementService {

    @Autowired
    private AppBereavementDao dao;

    @Override
    public List<Map<String, Object>> findBereavement(Integer category, String startTime, String endTime) {

        List<Map<String, Object>> bereavment = dao.findBereavement(category, startTime, endTime);

        return bereavment;
    }

    @Override
    public List<Map<String, Object>> findMoonEmotion(Integer category, String endTime) {
        List<Map<String, Object>> bereavment = dao.findMoonEmotion(category, endTime);
        return bereavment;

    }

    @Override
    public List<Map<String, Object>> frequencyCount(Integer app, String startTime, String endTime) {
        List<Map<String, Object>> mapList = dao.frequencyCount(app, startTime, endTime);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> findCategory(Integer category) {

        return dao.findCategory(category);
    }

    @Override
    public  List<Map<String, Object>> findParameter(Integer category, String startTime, String endTime) {
        List<Map<String, Object>> appName = dao.findCategory(category);
        List<Map<String, Object>> appNames = new ArrayList<>();
        for (Map<String, Object> map : appName) {
            Map<String, Object> maps=new HashMap<>();
            int app =(int) map.get("id");
            String name =(String) map.get("name");
            int freq_sum = dao.getFavorableRate(category, app, startTime, endTime);
            if(freq_sum==0){
                maps.put("name",name);
                maps.put("freq_sum",0);
                maps.put("freq_positive",0);
                maps.put("freq_neutral",0);
                maps.put("freq_negativity",0);
                maps.put("pet_positives","0.0%");
                maps.put("pet_negativitys","0.0%");
                maps.put("pet_neutrals","0.0%");
            }else {
                int freq_positive = dao.getFavorableRate1(category, app, startTime, endTime);
                int freq_neutral = dao.getFavorableRate2(category, app, startTime, endTime);
                int freq_negativity = dao.getFavorableRate3(category, app, startTime, endTime);
                double freq_sums = (double) freq_sum;
                double pet_positive = new BigDecimal((float) freq_positive / freq_sums * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String pet_positives = Double.toString(pet_positive);
                pet_positives = pet_positives + "%";
                double pet_negativity = new BigDecimal((float) freq_negativity / freq_sums * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String pet_negativitys = Double.toString(pet_negativity);
                pet_negativitys = pet_negativitys + "%";
                double pet_neutral = new BigDecimal((float) freq_neutral / freq_sums * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String pet_neutrals = Double.toString(pet_neutral);
                pet_neutrals = pet_neutrals + "%";

                maps.put("name",name);
                maps.put("freq_sum",freq_sum);
                maps.put("freq_positive",freq_positive);
                maps.put("freq_neutral",freq_neutral);
                maps.put("freq_negativity",freq_negativity);
                maps.put("pet_positives",pet_positives);
                maps.put("pet_negativitys",pet_negativitys);
                maps.put("pet_neutrals",pet_neutrals);

            }
            appNames.add(maps);
            }
         return appNames;



    }

}
