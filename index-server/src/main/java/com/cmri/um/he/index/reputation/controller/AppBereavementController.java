package com.cmri.um.he.index.reputation.controller;


import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.common.CalculateDaysByDate;
import com.cmri.um.he.index.common.Constants;
import com.cmri.um.he.index.common.DefaultTime;
import com.cmri.um.he.index.reputation.service.AppBereavementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 情感指数变化趋势
 *
 * @author yinjunjun
 * Created on 2018/8/10
 */
@RestController
@RequestMapping("/app-bereavement")
public class AppBereavementController extends ZRestController{
    @Autowired
    private AppBereavementService service;

    @RequestMapping(value = "/get-bereavement",method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam Integer category, String startTime,@RequestParam String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        List<Map<String, Object>> bereavement = null;
        if (startTime.equals("null")){
            try {
                String time = DefaultTime.getDefaultTimes(Constants.MONTH,5,endTime);
                bereavement = service.findBereavement(category, time, endTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            bereavement = service.findBereavement(category, startTime, endTime);
        }

        List list1=new ArrayList();
        List<String> dataMonth = new ArrayList<>();

        Map<String,Object> objectMap = new HashMap<>(16);
        for (Map<String, Object> map : bereavement) {
            String o = (String)map.get("name");
            String month =(String) map.get("month");
            dataMonth.add(month);
            if (objectMap.containsKey(o)){
                objectMap.put(o,objectMap.get(o)+","+map.get("value"));
            }else {
                objectMap.put(o,map.get("value"));
            }
        }
        HashSet h = new HashSet(dataMonth);
        dataMonth.clear();
        dataMonth.addAll(h);
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

        int size = month.size();
        int size1 = dataMonth.size();
        int i = 0;
        if (size>size1){
            i = size - size1;
        }

        Iterator it = objectMap.entrySet().iterator();
        while (it.hasNext()) {
            Map<String,Object> map1s = new HashMap<>(16);
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            map1s.put("name",key);
            String a = "";
            for (int j = 0; j < i; j++) {
                a += "0,";
            }
            map1s.put("value",a+value);
            list1.add(map1s);
        }

        List<Map<String, Object>> category1 = service.findCategory(category);
        List<Object> nameList = new ArrayList<>();
        for (Map<String, Object> mapp : category1) {
            Object name = mapp.get("name");
            nameList.add(name);
        }


        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("emotion",list1);
        responseMessage.set("month",month);
        return responseMessage;
    }

    @RequestMapping(value = "/get-MoonEmotion",method = RequestMethod.GET)
    public ResponseMessage findMoonEmotion(@RequestParam Integer category,@RequestParam String endTime) {
        List<Map<String, Object>> moonEmotion = service.findMoonEmotion(category, endTime);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items", moonEmotion);
        return responseMessage;
    }

    @RequestMapping(value = "/get-frequency",method = RequestMethod.GET)
    public ResponseMessage findFrequency(@RequestParam Integer app,String startTime,@RequestParam String endTime) throws Exception {

        List<Map<String, Object>> mapList = null;
        if (startTime.equals("null")){
            String time = DefaultTime.getDefaultTimes(Constants.MONTH,5,endTime);
            mapList = service.frequencyCount(app, time, endTime);
        } else {
            mapList = service.frequencyCount(app, startTime, endTime);
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        List<Map<String, Object>> mapList1 = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name","暂无数据");
        mapList1.add(map);
        if(mapList.size() == 0){
            responseMessage.set("items",mapList1);
        }else {
            responseMessage.set("items", mapList);
        }
        return responseMessage;
    }

    @RequestMapping(value = "/get-category",method = RequestMethod.GET)
    public ResponseMessage findCategory(@RequestParam Integer category) {
        List<Map<String, Object>> category1 = service.findCategory(category);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items", category1);
        return responseMessage;
    }

    @RequestMapping(value = "/get-parameter",method = RequestMethod.GET)
    public ResponseMessage findParameter(@RequestParam Integer category, String startTime,@RequestParam String endTime) throws Exception {
        List<Map<String, Object>> parameter = null;
        if (startTime.equals("null")){
            String time = CalculateDaysByDate.getDate(Constants.DAY,-30,endTime);
            parameter = service.findParameter(category,time,endTime);
        } else {
            parameter = service.findParameter(category, startTime, endTime);
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("items", parameter);
        return responseMessage;
    }


}
