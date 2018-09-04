package com.cmri.um.he.index.operations.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.operations.service.AppCmtariffService;
import com.cmri.um.he.index.operations.service.AppOperationExperienceService;
import com.cmri.um.he.index.operations.service.AppOperationsExcelExportService;
import com.cmri.um.he.index.operations.service.AppOriginalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 应用运营excel数据导入、导出接口
 *
 * @author lch
 * Created on 2018/08/13 10:47
 */
@RestController
@CrossOrigin
@RequestMapping("/excel")
public class AppOperationsExcelController extends ZRestController {
    @Autowired
    private AppOriginalContentService contentService;
    @Autowired
    private AppCmtariffService cmtariffService;
    @Autowired
    private AppOperationExperienceService experienceService;
    @Autowired
    private AppOperationsExcelExportService service;

    /**
     * 处理内容原始数据
     * @param files
     * @return
     */
    @RequestMapping(value = "/appOriginalContentExcel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage appOriginalContentExcel(List<MultipartFile> files) {
        String result = contentService.readExcelFile(files);
        ResponseMessage responseMessage = this.genResponseMessage();
        return responseMessage.setMsg(result);
    }
    /**
     * 处理渠道、服务、营销、资费原始数据
     * @param files
     * @return
     */
    @RequestMapping(value = "/appOriginalCmtariffExcel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage appOriginalCmtariffExcel(List<MultipartFile> files) {
        String result = cmtariffService.readExcelFile(files);
        ResponseMessage responseMessage = this.genResponseMessage();
        return responseMessage.setMsg(result);
    }


    /**
     * 处理运营体验得分
     * @param file
     * @return
     */
    @RequestMapping(value = "/appExperienceExcel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage appExperienceExcel(MultipartFile file) {
        String result = experienceService.readExcelFile(file);
        ResponseMessage responseMessage = this.genResponseMessage();
        return responseMessage.setMsg(result);
    }

    /**
     * 导出运营计算值
     */
    @RequestMapping(value = "/exportOperationsExcel", method = RequestMethod.GET)
    public void exportQualityExcel() {
        service.exportQualityExcel();
    }

    /**
     * 导出品质得分值
     */
    @RequestMapping(value = "/exportContentScore", method = RequestMethod.GET)
    public void exportContentScore(String month) {
        service.exportContentScore(month);
    }
}
