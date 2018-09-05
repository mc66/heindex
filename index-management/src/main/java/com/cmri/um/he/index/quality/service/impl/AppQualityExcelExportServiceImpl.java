package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.AppQualityExcelExportDao;
import com.cmri.um.he.index.quality.service.AppQualityExcelExportService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lch
 * Created on 2018/08/31 16:41
 */
@Service
public class AppQualityExcelExportServiceImpl implements AppQualityExcelExportService{

    @Autowired
    private AppQualityExcelExportDao excelExportDao;
    @Override
    public void exportQualityExcel() {
        List<Map<String, Object>> list = excelExportDao.getAllCalculationQuality();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("品质计算值表");
        XSSFRow row1=sheet.createRow(0);
        //创建单元格并设置单元格内容
        row1.createCell(0).setCellValue("测评阶段");
        row1.createCell(1).setCellValue("产品类别");
        row1.createCell(2).setCellValue("产品名称");
        row1.createCell(3).setCellValue("时延");
        row1.createCell(4).setCellValue("功耗");
        row1.createCell(5).setCellValue("功能");
        row1.createCell(6).setCellValue("界面");
        row1.createCell(7).setCellValue("使用体验");
        int i=1;
        for (Map<String, Object> entity : list) {
            XSSFRow row=sheet.createRow(i);
            row.createCell(0).setCellValue((String) entity.get("month"));
            row.createCell(1).setCellValue((String) entity.get("category"));
            row.createCell(2).setCellValue((String) entity.get("app"));
            row.createCell(3).setCellValue((Double) entity.get("delay"));
            row.createCell(4).setCellValue((Double) entity.get("consume"));
            row.createCell(5).setCellValue((Double) entity.get("features"));
            row.createCell(6).setCellValue((Double) entity.get("view"));
            row.createCell(7).setCellValue((Double) entity.get("experience"));
            i++;
        }
        //输出Excel文件
        try {
            File file=new File("D:/计算值");
            if(!file.exists()){
                file.mkdir();
            }
            wb.write(new FileOutputStream(new File("D:/计算值/品质计算值表.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportWeightExcel() {
        List<Map<String, Object>> list = excelExportDao.getAllWeight();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("权重表");
        XSSFRow row1=sheet.createRow(0);
        //创建单元格并设置单元格内容
        row1.createCell(0).setCellValue("应用类别");
        row1.createCell(1).setCellValue("3G权重");
        row1.createCell(2).setCellValue("4G权重");
        row1.createCell(3).setCellValue("WLAN权重");
        row1.createCell(4).setCellValue("高严重等级权重");
        row1.createCell(5).setCellValue("中严重等级权重");
        row1.createCell(6).setCellValue("低严重等级权重");
        row1.createCell(7).setCellValue("延时权重");
        row1.createCell(8).setCellValue("功耗权重");
        row1.createCell(9).setCellValue("功能权重");
        row1.createCell(10).setCellValue("界面权重");
        row1.createCell(11).setCellValue("品质体验权重");
        row1.createCell(12).setCellValue("内容权重");
        row1.createCell(13).setCellValue("渠道权重");
        row1.createCell(14).setCellValue("营销权重");
        row1.createCell(15).setCellValue("资费权重");
        row1.createCell(16).setCellValue("服务权重");
        row1.createCell(17).setCellValue("运营体验权重");
        int i=1;
        for (Map<String, Object> entity : list) {
            XSSFRow row=sheet.createRow(i);
            row.createCell(0).setCellValue((String) entity.get("category"));
            row.createCell(1).setCellValue((Double) entity.get("w3g"));
            row.createCell(2).setCellValue((Double) entity.get("w4g"));
            row.createCell(3).setCellValue((Double) entity.get("wwlan"));
            row.createCell(4).setCellValue((Integer)entity.get("whigh"));
            row.createCell(5).setCellValue((Integer) entity.get("wmiddle"));
            row.createCell(6).setCellValue((Integer) entity.get("wlow"));
            row.createCell(7).setCellValue((Double) entity.get("wdelay"));
            row.createCell(8).setCellValue((Double) entity.get("wconsume"));
            row.createCell(9).setCellValue((Double) entity.get("wfeatures"));
            row.createCell(10).setCellValue((Double) entity.get("wview"));
            row.createCell(11).setCellValue((Double) entity.get("wexperience"));
            row.createCell(12).setCellValue((Double) entity.get("wcontent"));
            row.createCell(13).setCellValue((Double) entity.get("wchannel"));
            row.createCell(14).setCellValue((Double) entity.get("wmarket"));
            row.createCell(15).setCellValue((Double) entity.get("wexpenses"));
            row.createCell(16).setCellValue((Double) entity.get("wservice"));
            row.createCell(17).setCellValue((Double) entity.get("wexperience_operation"));
            i++;
        }
        //输出Excel文件
        try {
            File file=new File("D:/计算值");
            if(!file.exists()){
                file.mkdir();
            }
            wb.write(new FileOutputStream(new File("D:/计算值/权重表.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
