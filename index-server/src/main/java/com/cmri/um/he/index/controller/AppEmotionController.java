package com.cmri.um.he.index.controller;


import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppEmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 口碑指数，当前年应用的情感得分
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@RestController
@RequestMapping("/app-emotion")
@CrossOrigin
public class AppEmotionController extends ZRestController{

    @Autowired
    private AppEmotionService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam Integer category){
        List<Map<String, Object>> maps = service.find(category);
        Map<String,Object> ma = new HashMap(16);
        List list=new ArrayList();
        for (Map<String, Object> map : maps) {
            String o =(String) map.get("app");
            if (ma.containsKey(o)){
                ma.put(o,ma.get(o)+","+map.get("emotion"));
            }else {
                ma.put(o,map.get("emotion"));
            }

      }

        Iterator it = ma.entrySet().iterator();
        while (it.hasNext()) {
            Map<String,Object> mapp = new HashMap<>(16);
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            mapp.put("name",key);
            mapp.put("emotion",value);
            list.add(mapp);
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("emotion",list);
        responseMessage.set("emotions",maps);
        return responseMessage;
    }

}
