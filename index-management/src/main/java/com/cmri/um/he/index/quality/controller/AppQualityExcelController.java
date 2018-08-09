package com.cmri.um.he.index.quality.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.service.AppCalculationQualityService;
import com.cmri.um.he.index.quality.service.AppOrginalFeaturesService;
import com.cmri.um.he.index.quality.service.AppOriginalDelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 应用品质excel数据导入接口
 * @author lch
 * Created on 2018/08/03 10:50
 */
@RestController
@CrossOrigin
@RequestMapping("/excel")
public class AppQualityExcelController extends ZRestController {

    @Autowired
    private AppOriginalDelayService delayExcelService;
    @Autowired
    private AppOrginalFeaturesService featuresService;
    @Autowired
    private AppCalculationQualityService appCalculationQualityService;

    /**
     * 处理时延、功耗原始数据
     * @param file
     * @return
     */
    @RequestMapping(value = "/appOriginalDelay", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage appOriginalDelayExcel(MultipartFile file) {
        String result = delayExcelService.readExcelFile(file);
        ResponseMessage responseMessage = this.genResponseMessage();
        return responseMessage.setMsg(result);
    }

    /**
     * 处理功能、界面原始数据
     * @param file
     * @return
     */
    @RequestMapping(value = "/appOriginalFeatures", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage appOriginalFeaturesExcel(MultipartFile file) {
        List list = featuresService.readExcelFile(file);
        //保存
        boolean b= featuresService.saveList(list);
        //计算得分
        AppCalculationQualityEntity appCalculationQualityEntity= featuresService.getScore(list);
        //保存得分appCalculationQualityEntity
        int m=appCalculationQualityService.update(appCalculationQualityEntity);
        ResponseMessage responseMessage = this.genResponseMessage();
        if(b==true&&m>0){
            responseMessage.setMsg("提交成功");
        }else {
            responseMessage.setMsg("提交失败");
        }
        return responseMessage;
    }

    /**
     * 处理功能、界面原始数据
     * @param file
     * @return
     */
    @RequestMapping(value = "/appOriginalExperience", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage appOriginalExperienceExcel(MultipartFile file) {
        List list = featuresService.readExcelFile(file);
        //保存
        boolean b= featuresService.saveList(list);
        //计算得分
        AppCalculationQualityEntity appCalculationQualityEntity= featuresService.getScore(list);
        //保存得分appCalculationQualityEntity
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
