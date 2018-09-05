package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppOperationsExcelExportDao;
import com.cmri.um.he.index.operations.service.AppOperationsExcelExportService;
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
public class AppOperationsExcelExportServiceImpl implements AppOperationsExcelExportService {

    @Autowired
    private AppOperationsExcelExportDao excelExportDao;
    @Override
    public void exportQualityExcel() {
        List<Map<String, Object>> list = excelExportDao.getAllCalculationQuality();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("运营计算值表");
        XSSFRow row1=sheet.createRow(0);
        //创建单元格并设置单元格内容
        row1.createCell(0).setCellValue("测评阶段");
        row1.createCell(1).setCellValue("产品类别");
        row1.createCell(2).setCellValue("产品名称");
        row1.createCell(3).setCellValue("内容");
        row1.createCell(4).setCellValue("渠道");
        row1.createCell(5).setCellValue("资费");
        row1.createCell(6).setCellValue("服务");
        row1.createCell(7).setCellValue("营销");
        row1.createCell(8).setCellValue("运营体验");
        int i=1;
        for (Map<String, Object> entity : list) {
            XSSFRow row=sheet.createRow(i);
            row.createCell(0).setCellValue((String) entity.get("month"));
            row.createCell(1).setCellValue((String) entity.get("category"));
            row.createCell(2).setCellValue((String) entity.get("app"));
            row.createCell(3).setCellValue((Double) entity.get("content"));
            row.createCell(4).setCellValue((Double) entity.get("channel"));
            row.createCell(5).setCellValue((Double) entity.get("tariff"));
            row.createCell(6).setCellValue((Double) entity.get("service"));
            row.createCell(7).setCellValue((Double) entity.get("market"));
            row.createCell(8).setCellValue((Double) entity.get("experience"));
            i++;
        }
        //输出Excel文件
        try {
            File file=new File("D:/计算值");
            if(!file.exists()){
                file.mkdir();
            }
            wb.write(new FileOutputStream(new File("D:/计算值/运营计算值表.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
