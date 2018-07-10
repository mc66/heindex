package com.cmri.um.he.index.quality.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppOriginalFeaturesEntity;
import com.cmri.um.he.index.quality.service.AppCalculationQualityService;
import com.cmri.um.he.index.quality.service.AppOrginalFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 某个app的功能界面原始数据
 *
 * @author shihao
 * Created on 2018/6/24
 */
@RestController
@CrossOrigin
public class AppOrginalFeaturesController extends ZRestController {

    @Autowired
    private AppOrginalFeaturesService appOrginalFeaturesService;
    @Autowired
    private AppCalculationQualityService appCalculationQualityService;


    /**
     * 新增和批量新增并保存某app的功能和界面得分
     * @param map
     * @return
     */
    @RequestMapping(value = "/app-original-features-saveList", method = RequestMethod.POST)
    public ResponseMessage saveList(@RequestBody Map<String , List<AppOriginalFeaturesEntity>> map){
        List<AppOriginalFeaturesEntity> list = map.get("data");
        //保存
        boolean b= appOrginalFeaturesService.saveList(list);
        //计算得分
        AppCalculationQualityEntity appCalculationQualityEntity= appOrginalFeaturesService.getScore(list);
        //保存得分
        int m=appCalculationQualityService.update(appCalculationQualityEntity);
        ResponseMessage responseMessage = this.genResponseMessage();
        if(b==true&&m>0){
            responseMessage.setMsg("提交成功");
        }else {
            responseMessage.setMsg("提交失败");
        }

        return responseMessage;

    }

}
