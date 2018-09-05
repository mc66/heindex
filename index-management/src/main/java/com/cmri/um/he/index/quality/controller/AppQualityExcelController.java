package com.cmri.um.he.index.quality.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.quality.service.AppOrginalFeaturesService;
import com.cmri.um.he.index.quality.service.AppOriginalDelayService;
import com.cmri.um.he.index.quality.service.AppOriginalExperienceService;
import com.cmri.um.he.index.quality.service.AppQualityExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 应用品质excel数据导入、导出接口
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
    private AppOriginalExperienceService experienceService;
    @Autowired
    private AppQualityExcelExportService exportService;

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
        String result = featuresService.readExcelFile(file);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.setMsg(result);
        return responseMessage;
    }

    /**
     * 处理应用体验原始数据
     * @param file
     * @return
     */
    @RequestMapping(value = "/appOriginalExperience", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage appOriginalExperienceExcel(MultipartFile file) {
        String result = experienceService.readExcelFile(file);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.setMsg(result);
        return responseMessage;
    }

    /**
     * 导出品质计算值
     */
    @RequestMapping(value = "/exportQuality", method = RequestMethod.GET)
    public void exportQualityExcel() {
        exportService.exportQualityExcel();
    }

    /**
     * 导出权重表
     */
    @RequestMapping(value = "/exportWeight", method = RequestMethod.GET)
    public void exportWeightExcel() {
        exportService.exportWeightExcel();
    }
        /**
     * 导出功能界面计算值
     */
    @RequestMapping(value = "/exportFeatures", method = RequestMethod.GET)
    public void exportFeaturesExcel() {

        exportService.exportFeaturesExcel();
    }
}
