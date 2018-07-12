package com.cmri.um.he.index.operations.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.service.AppOperationExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 处理app运营体验得分
 * @author machao
 * Created on 2018/7/11
 */
@RestController
@CrossOrigin
public class AppOperationExperienceController extends ZRestController {

    @Autowired
    AppOperationExperienceService appOperationExperienceService;

    /**
     * 将运营体验得分存入数据库
     * @param map
     * @return
     */
    @RequestMapping(value = "/operations-experience",method = RequestMethod.POST)
    public ResponseMessage disposeOperationExperience(@RequestBody Map<String,List<AppCalculationOperationsEntity>> map){
        List<AppCalculationOperationsEntity> list = map.get("data");
        ResponseMessage responseMessage = this.genResponseMessage();
        for (AppCalculationOperationsEntity appCalculationOperationsEntity : list) {
            AppCalculationOperationsEntity acoe=appOperationExperienceService.find(appCalculationOperationsEntity);
            if (acoe!=null){
                appOperationExperienceService.update(acoe.getId(),appCalculationOperationsEntity.getExperience());
            }else {
                appOperationExperienceService.add(appCalculationOperationsEntity);
            }
        }
        return responseMessage;
    }

}
