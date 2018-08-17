package com.cmri.um.he.index.monument.service.impl;

import com.cmri.um.he.index.common.CalculateDaysByDate;
import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.monument.dao.AppBereavementDao;
import com.cmri.um.he.index.monument.service.AppBereavementService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            Map<String, Object> list = new HashMap<>();
            int id =(int) map.get("id");
            String name =(String) map.get("name");
            List<Map<String, Object>> positive = dao.findPositive(id, startTime, endTime);
            for (Map<String, Object> objectMap : positive) {

                BigDecimal o =(BigDecimal) objectMap.get("freq_positive");
                BigDecimal o1 = (BigDecimal)objectMap.get("freq_negativity");
                BigDecimal o2 =(BigDecimal) objectMap.get("freq_neutral");
                BigDecimal o3 = (BigDecimal)objectMap.get("freq_sum");
                String rate1 = null;
                String rate2 = null;
                String rate3 = null;
                if (o3.compareTo(BigDecimal.ZERO)==0){
                    rate1 = "0%";
                    rate2 = "0%";
                    rate3 = "0%";
                }else {
                    rate1 = (o.divide(o3,2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100))+"%";
                    rate2 = (o1.divide(o3,2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100))+"%";
                    rate3 = (o2.divide(o3,2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100))+"%";
                }
                list.put("freq_positive",o);
                list.put("freq_negativity",o1);
                list.put("freq_neutral",o2);
                list.put("freq_sum",o3);
                list.put("rate_positive",rate1);
                list.put("rate_negativity",rate2);
                list.put("rate_neutral",rate3);

            }

            list.put("name", name);
            appNames.add(list);
        }

        return appNames;
    }

}
