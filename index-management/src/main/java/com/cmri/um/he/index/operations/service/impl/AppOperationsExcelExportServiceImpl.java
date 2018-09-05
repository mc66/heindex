package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppOperationsExcelExportDao;
import com.cmri.um.he.index.operations.service.AppOperationsExcelExportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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
            wb.write(new FileOutputStream(new File("d:/运营计算值表.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 体验指数-产品运营-导出内容得分方法
     */
    @Override
    public void exportContentScore(String month) {
        List<Map<String, Object>> contentScore = getContentScore(month);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet contentScoreTable = workbook.createSheet("品质的分表");
        XSSFRow row1=contentScoreTable.createRow(0);
        //创建单元格并设置单元格内容
        row1.createCell(0).setCellValue("测评时间");
        row1.createCell(1).setCellValue("产品名称");
        row1.createCell(2).setCellValue("内容覆盖样本库数量");
        row1.createCell(3).setCellValue("内容更新样本库数量");
        row1.createCell(4).setCellValue("内容推荐样本库数量");
        row1.createCell(5).setCellValue("内容覆盖测量值为1的数量");
        row1.createCell(6).setCellValue("内容更新测量值为1的数量");
        row1.createCell(7).setCellValue("内容推荐测量值为1的数量");
        row1.createCell(8).setCellValue("内容覆盖得分");
        row1.createCell(9).setCellValue("内容更新得分");
        row1.createCell(10).setCellValue("内容推荐得分");
        row1.createCell(11).setCellValue("内容平均得分");

        int i = 1;
        for (Map<String, Object> map : contentScore) {
            XSSFRow row=contentScoreTable.createRow(i);
            row.createCell(0).setCellValue((String) map.get("month"));
            row.createCell(1).setCellValue((String) map.get("name"));
            row.createCell(2).setCellValue((Long) map.get("coverSum"));
            row.createCell(3).setCellValue((Long) map.get("updateSum"));
            row.createCell(4).setCellValue((Long) map.get("recommendSum"));
            row.createCell(5).setCellValue((Long) map.get("coverNum"));
            row.createCell(6).setCellValue((Long) map.get("updateNum"));
            row.createCell(7).setCellValue((Long) map.get("recommendNum"));
            row.createCell(8).setCellValue((Double) map.get("coverScore"));
            row.createCell(9).setCellValue((Double) map.get("updateScore"));
            row.createCell(10).setCellValue((Double) map.get("recommendScore"));
            row.createCell(11).setCellValue((Double) map.get("contentScore"));
            i++;
        }
        //输出Excel文件
        try {
            File file = new File("F:/计算值");
            workbook.write(new FileOutputStream(new File("F:/内容得分表.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String,Object>> getContentScore(String month){
        //获取当前期数所有app
        List<Map<String,Object>> list =excelExportDao.quaryAll(month);
        for (Map<String, Object> map : list) {
            Integer app = (Integer) map.get("app");
            String month1 = (String) map.get("month");

            //查询当前app三个类别的样本数
            Map<String,Long> sampleSumMap = excelExportDao.quarySampleSum(app,month1);
            Long coverSum = sampleSumMap.get("coverSum");
            double cs = coverSum.doubleValue();
            Long updateSum = sampleSumMap.get("updateSum");
            double us = updateSum.doubleValue();
            Long recommendSum = sampleSumMap.get("recommendSum");
            double rs = recommendSum.doubleValue();
            map.put("coverSum",coverSum);
            map.put("updateSum",updateSum);
            map.put("recommendSum",recommendSum);

            //查询当前app三个类别测量值记为1的数量
            Map<String,Long> sampleNumMap = excelExportDao.quarySampleNum(app,month1);
            Long coverNum = sampleNumMap.get("coverNum");
            double cn = coverNum.doubleValue();
            Long updateNum = sampleNumMap.get("updateNum");
            double un = updateNum.doubleValue();
            Long recommendNum = sampleNumMap.get("recommendNum");
            double rn = recommendNum.doubleValue();
            map.put("coverNum",coverNum);
            map.put("updateNum",updateNum);
            map.put("recommendNum",recommendNum);

            double coverScore = 0;
            double updateScore = 0;
            double recommendScore = 0;
            //分别计算三个内容类得分
            if (coverSum!=0){
                coverScore = new BigDecimal( cn/cs*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            if (updateSum!=0){
                updateScore = new BigDecimal( un/us*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            if (recommendSum!=0){
                recommendScore = new BigDecimal( rn/rs*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }

            map.put("coverScore",coverScore);
            map.put("updateScore",updateScore);
            map.put("recommendScore",recommendScore);

            //计算内容得分
            map.put("contentScore",new BigDecimal( (coverScore+updateScore+recommendScore)/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return list;
    }
}
