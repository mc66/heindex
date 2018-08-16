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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        List<Map<String, Object>> bereavment = dao.findBereavement(category, startTime, endTime);
        List list1=new ArrayList();
        List<Map<String, Object>> mapList = new ArrayList<>(16);
        Map<String,Object> objectMap = new HashMap<>(16);
        for (Map<String, Object> map : bereavment) {
            String o = (String)map.get("name");
            if (objectMap.containsKey(o)){
                objectMap.put(o,objectMap.get(o)+","+map.get("value"));
            }else {
                objectMap.put(o,map.get("value"));
            }
        }
        Iterator it = objectMap.entrySet().iterator();
        while (it.hasNext()) {
            Map<String,Object> map1s = new HashMap<>(16);
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            map1s.put("name",key);
            map1s.put("value",value);
            list1.add(map1s);
        }
        objectMap.put("qq",list1);

        List<Map<String, Object>> category1 = dao.findCategory(category);
        List<Object> nameList = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        for (Map<String, Object> mapp : category1) {
            Object name = mapp.get("name");
            Integer id = (Integer) mapp.get("id");
            nameList.add(name);
            idList.add(id);
        }

        Map<String, Object> namemap = new HashMap<>(16);
        Map<String, Object> monthmap = new HashMap<>(16);
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
        mapList.add(objectMap);
        mapList.add(namemap);
        mapList.add(monthmap);

        return mapList;
    }

    @Override
    public List<Map<String, Object>> findMoonEmotion(Integer category, String endTime) {
        List<Map<String, Object>> bereavment = dao.findMoonEmotion(category, endTime);
        return bereavment;

    }


   @Override
    public List<Map<String, Object>> findNumberCommentsJust(Integer category, String startTime, String endTime) {
        if (startTime.equals("null")) {
            try {
                String defaultTime = CalculateDaysByDate.getDate(Constants.DAY,-30,endTime);
                return  dao.getNumberCommentsJust(category, defaultTime, endTime);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return dao.getNumberCommentsJust(category, startTime, endTime);
        }
    }

    @Override
    public List<Map<String, Object>> findAppName(Integer category, String startTime, String endTime) {
        if (startTime.equals("null")) {
            try {
                String defaultTime = CalculateDaysByDate.getDate(Constants.DAY,-30,endTime);
                return  dao.findAppName(category, defaultTime, endTime);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return dao.findAppName(category, startTime, endTime);
        }
    }

    @Override
    public List<Map<String, Object>> findNumberCommentsCentre(Integer category, String startTime, String endTime) {
        if (startTime.equals("null")) {
            try {
                String defaultTime = CalculateDaysByDate.getDate(Constants.DAY,-30,endTime);
                return  dao.findNumberCommentsCentre(category, defaultTime, endTime);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return dao.findNumberCommentsCentre(category, startTime, endTime);
        }
    }

    @Override
    public List<Map<String, Object>> findNumberCommentsLoad(Integer category, String startTime, String endTime) {
        if (startTime.equals("null")) {
            try {
                String defaultTime = CalculateDaysByDate.getDate(Constants.DAY,-30,endTime);
                return  dao.findNumberCommentsLoad(category, defaultTime, endTime);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return dao.findNumberCommentsLoad(category, startTime, endTime);
        }
    }
}
