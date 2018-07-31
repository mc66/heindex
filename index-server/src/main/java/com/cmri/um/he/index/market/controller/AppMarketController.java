package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.AppMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 市场指数的关键指标数据
 * @author shihao/machao
 * Created on 2018/6/13
 */
@RestController
@CrossOrigin
public class AppMarketController extends ZRestController {

    @Autowired
    private AppMarketService appMarketService;

    @RequestMapping(value = "/app-market-list",method = RequestMethod.GET)
    public ResponseMessage getAppMarket(@RequestParam int category,@RequestParam String month){
        List<Map<String, Object>> appMarketList = appMarketService.getAppMarketList(category,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (appMarketList!=null&&appMarketList.size()>0){
            responseMessage.set("marketList",appMarketList);
        }else {
            responseMessage.setMsg("现在还没有数据！");
        }
        return responseMessage;
    }

    @RequestMapping(value = "app-market-query",method = RequestMethod.GET)
    public ResponseMessage getMarket(@RequestParam Integer category ,@RequestParam String month1,@RequestParam String month2,@RequestParam String status ){

        List<Map<String ,Object>> list=appMarketService.getMarket(category,month1,month2,status);;

        Map<String,Object> map1 = new HashMap();
        Map<String,Object> map2 = new HashMap();
        Map<String,Object> map3 = new HashMap();
        Map<String,Object> map4 = new HashMap();
        List list1=new ArrayList();
        List list2=new ArrayList();
        List list3=new ArrayList();
        List list4=new ArrayList();

        for (Map<String, Object> objectMap : list) {
            String  appName=(String) objectMap.get("name");
            if(map1.containsKey(appName)){
                map1.put(appName,map1.get(appName)+","+objectMap.get("mau_number"));
            }else {
                map1.put(appName,objectMap.get("mau_number"));
            }
            if(map2.containsKey(appName)){
                map2.put(appName,map2.get(appName)+","+objectMap.get("keep_rate"));
            }else {
                map2.put(appName,objectMap.get("keep_rate"));
            }
            if(map3.containsKey(appName)){
                map3.put(appName,map3.get(appName)+","+objectMap.get("length_time"));
            }else {
                map3.put(appName,objectMap.get("length_time"));
            }
            if(map4.containsKey(appName)){
                map4.put(appName,map4.get(appName)+","+objectMap.get("flow"));
            }else {
                map4.put(appName,objectMap.get("flow"));
            }

        }
        Iterator it = map1.entrySet().iterator();
        while (it.hasNext()) {
            Map<String,Object> map1s = new HashMap<>(16);
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            map1s.put("name",key);
            map1s.put("mau_number",value);
            list1.add(map1s);
        }
        Iterator it2 = map1.entrySet().iterator();
        while (it2.hasNext()) {
            Map<String,Object> map2s = new HashMap<>(16);
            Map.Entry entry = (Map.Entry) it2.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            map2s.put("name",key);
            map2s.put("keep_rate",value);
            list2.add(map2s);
        }
        Iterator it3 = map1.entrySet().iterator();
        while (it3.hasNext()) {
            Map<String,Object> map3s = new HashMap<>(16);
            Map.Entry entry = (Map.Entry) it3.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            map3s.put("name",key);
            map3s.put("length_time",value);
            list3.add(map3s);
        }
        Iterator it4 = map1.entrySet().iterator();
        while (it4.hasNext()) {
            Map<String,Object> map4s = new HashMap<>(16);
            Map.Entry entry = (Map.Entry) it4.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            map4s.put("name",key);
            map4s.put("flow",value);
            list4.add(map4s);
        }
            ResponseMessage responseMessage=this.genResponseMessage();
        if (list!=null&&list.size()>0){
            responseMessage.set("mau_number",list1);
            responseMessage.set("keep_rate",list2);
            responseMessage.set("length_time",list3);
            responseMessage.set("flow",list4);
            responseMessage.set("list",list);
        }else {
            responseMessage.setMsg("未添加数据！");
        }
        return responseMessage;

    }



}
