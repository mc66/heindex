package com.cmri.um.he.index.monument.service.impl;

import com.cmri.um.he.index.common.CalculateDaysByDate;
import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.monument.dao.AppBereavementDao;
import com.cmri.um.he.index.monument.service.AppBereavementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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
    public List<Map<String, List>> findBereavement(Integer category, String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        List<Map<String, Object>> bereavment = dao.findBereavement(category, startTime, endTime);
        Set<String> set=new HashSet();
        for (Map<String, Object> map : bereavment) {
            String name= (String) map.get("name");
            String key=name;
            set.add(key);
        }
        List<Map<String, List>> mapList = new ArrayList<>();
        Map<String,List> map=new HashMap();
        for (String s : set) {
            map.put(s,new ArrayList<>());
        }
        for (Map<String, Object> map1 : bereavment) {
            String name= (String) map1.get("name");
            String key=name;
            List list = map.get(key);
            list.add(map1.get("emotion_score"));
            map.put(key,list);
        }
        List<Map<String, Object>> category1 = dao.findCategory(category);
        List<Object> nameList = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        for (Map<String, Object> mapp : category1) {
            Object name = mapp.get("name");
            Integer id = (Integer) mapp.get("id");
            nameList.add(name);
            idList.add(id);
        }


        Map<String, List> namemap = new HashMap<>(16);
        Map<String, List> monthmap = new HashMap<>(16);
        namemap.put("app",nameList);
        List<String> month = new ArrayList<>();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        try {
            min.setTime(sdf.parse(startTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        try {
            max.setTime(sdf.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        max.set(max.get(Calendar.YEAR),max.get(Calendar.MONTH), 2);
        Calendar curr = min;
        while (curr.before(max)) {
            month.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        monthmap.put("month",month);
        mapList.add(map);
        mapList.add(namemap);
        mapList.add(monthmap);

        return mapList;
    }

    @Override
    public List<Map<String, Object>> findMoonEmotion(Integer category, String endTime) {
        List<Map<String, Object>> bereavment = dao.findMoonEmotion(category, endTime);
        return bereavment;

    }

}
