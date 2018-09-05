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
    /**
     * 导出功能界面计算值
     */
    @Override
    public void exportFeaturesExcel() {
        List<Map<String,Object>> list=excelExportDao.getMonth();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("功能界面计算值表");
        XSSFRow rows=sheet.createRow(0);
        //创建单元格并设置单元格内容
        rows.createCell(0).setCellValue("测评阶段");
        rows.createCell(1).setCellValue("产品类别");
        rows.createCell(2).setCellValue("产品名称");
        rows.createCell(3).setCellValue("问题维度");
        rows.createCell(4).setCellValue("高等级数量");
        rows.createCell(5).setCellValue("中等级数量");
        rows.createCell(6).setCellValue("低等级数量");

        int i=1;
        for (Map<String, Object> stringObjectMap : list) {
            String month=(String)stringObjectMap.get("month");
            List<Map<String,Object>> list1=excelExportDao.getAPP(month);
            for (Map<String, Object> objectMap : list1) {
                int app=(int)objectMap.get("app");
                String appName =(String)objectMap.get("appName");
                String categoryName =(String)objectMap.get("categoryName");
                List<Map<String,Object>> list2=excelExportDao.getWeight(app,month);
                for (Map<String, Object> map : list2) {
                    String dimensions=(String) map.get("dimensions");
                    int count1=excelExportDao.getCount1(app,month,dimensions);
                    int count2=excelExportDao.getCount2(app,month,dimensions);
                    int count3=excelExportDao.getCount3(app,month,dimensions);

                    XSSFRow row5=sheet.createRow(i);
                    row5.createCell(0).setCellValue(month);
                    row5.createCell(1).setCellValue(categoryName);
                    row5.createCell(2).setCellValue(appName);
                    row5.createCell(3).setCellValue(dimensions);
                    row5.createCell(4).setCellValue(count1);
                    row5.createCell(5).setCellValue(count2);
                    row5.createCell(6).setCellValue(count3);
                    i++;
                }
            }
        }
        //输出Excel文件
        try {
            File fileDir = new File("F:/计算值");
            wb.write(new FileOutputStream(new File("F:/功能界面计算值表.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
