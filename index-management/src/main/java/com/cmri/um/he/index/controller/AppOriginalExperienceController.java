package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppOriginalExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询某类未添加应用体验接口
 *
 * @author limin
 * Created on 2018/6/26
 */
@RestController
@RequestMapping("/app-quality-original-experience-list")
@CrossOrigin
public class AppOriginalExperienceController  extends ZRestController {

    @Autowired
    private AppOriginalExperienceService appOriginalExperienceService;

    /**
     *
      * @return
     */
    @RequestMapping("findWhole")
    public ResponseMessage findWhole() {
        List<Map<String, Object>> maps = appOriginalExperienceService.findWhole();
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("emotions",maps);
        return responseMessage;
    }

    /**
     *
     * @param category
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get( @RequestParam Integer category) {
        List<Map<String, Object>> maps = appOriginalExperienceService.find( category);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("emotions",maps);
        return responseMessage;
    }


    @RequestMapping("updateExperience")
    public Map<String,Object> updateExperience( @RequestParam Integer id,@RequestParam Double experience) {
        Map<String,Object> map = new HashMap<>(16);
         int n = appOriginalExperienceService.updateExperience( id,experience);
         if(n > 0){
             map.put("code",200 );
             map.put("succeed",true);
             map.put("msg","修改成功");
         }else {
             map.put("code",500 );
             map.put("succeed",false);
             map.put("msg","修改失败");
         }
        return map;
    }




}
