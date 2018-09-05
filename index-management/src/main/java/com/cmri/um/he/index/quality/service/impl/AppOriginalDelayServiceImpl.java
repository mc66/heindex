package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.AppExcelDao;
import com.cmri.um.he.index.quality.dao.AppOriginalDelayDao;
import com.cmri.um.he.index.quality.dao.AppWeightQualityDao;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppOriginalDelayEntity;
import com.cmri.um.he.index.quality.entity.AppWeightQualityEntity;
import com.cmri.um.he.index.quality.service.AppOriginalDelayService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 品质得分中时延、功耗
 * @author lch
 * Created on 2018/06/27 09:55
 */
@Service
public class AppOriginalDelayServiceImpl implements AppOriginalDelayService {
    @Autowired
    private AppOriginalDelayDao delayDao;
    @Autowired
    private AppWeightQualityDao weightQualityDao;
    @Autowired
    private AppExcelDao excelDao;

    private final static DecimalFormat DF = new DecimalFormat("####0.00");

    @Override
    public List<Map<String, Object>> findAppByCategory(Integer category) {
        return delayDao.findAppByCategory(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAppOriginalDelayList(List<AppOriginalDelayEntity> list) {
        Date date = new Date();
        for (AppOriginalDelayEntity entity : list) {
            //double格式化
            String measure = DF.format(entity.getMeasure());
            entity.setMeasure(Double.valueOf(measure));
            String standard = DF.format(entity.getStandard());
            entity.setStandard(Double.valueOf(standard));
            String challenge = DF.format(entity.getChallenge());
            entity.setChallenge(Double.valueOf(challenge));
            entity.setAtime(date);
            //1--正常 0--已删除
            entity.setStatus(1);
            delayDao.saveOriginal(entity);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean dealAppOriginalDelayList(List<AppOriginalDelayEntity> list) {
        //查询权重配置
        int category = list.get(0).getCategory();
        AppWeightQualityEntity qualityConfig = weightQualityDao.findQualityConfig(category);
        double delay=0;
        double consume=0;
        if (list.size()==14){
            //启动时延
            AppOriginalDelayEntity entity1 = list.get(0);
            AppOriginalDelayEntity entity2 = list.get(7);
            double s1 = dealNo3gEntityList(entity1, entity2, qualityConfig);
            //浏览时延
            AppOriginalDelayEntity entity4 = list.get(1);
            AppOriginalDelayEntity entity5 = list.get(8);
            double s2 = dealNo3gEntityList(entity4, entity5, qualityConfig);
            //上传速率
            AppOriginalDelayEntity entity7 = list.get(2);
            AppOriginalDelayEntity entity8 = list.get(9);
            double s3 = dealNo3gEntityList(entity7, entity8, qualityConfig);
            //下载速率
            AppOriginalDelayEntity entity10 = list.get(3);
            AppOriginalDelayEntity entity11 = list.get(10);
            double s4 = dealNo3gEntityList(entity10, entity11, qualityConfig);
            //延时计算值
            delay = (s1 + s2 + s3 + s4) / 4;
            //CPU耗电量
            AppOriginalDelayEntity entity13 = list.get(4);
            AppOriginalDelayEntity entity14 = list.get(11);
            double s5 = dealNo3gEntityList(entity13, entity14,qualityConfig);
            //CPU占用峰值
            AppOriginalDelayEntity entity16 = list.get(5);
            AppOriginalDelayEntity entity17 = list.get(12);
            double s6 = dealNo3gEntityList(entity16, entity17, qualityConfig);
            //内存占用峰值
            AppOriginalDelayEntity entity19 = list.get(6);
            AppOriginalDelayEntity entity20 = list.get(13);
            double s7 = dealNo3gEntityList(entity19, entity20, qualityConfig);
            //功耗计算值
            consume = (s5 + s6 + s7) / 3;
        }else if(list.size()==21){
            //启动时延
            AppOriginalDelayEntity entity1 = list.get(0);
            AppOriginalDelayEntity entity2 = list.get(7);
            AppOriginalDelayEntity entity3 = list.get(14);
            double s1 = dealEntityList(entity1, entity2, entity3, qualityConfig);
            //浏览时延
            AppOriginalDelayEntity entity4 = list.get(1);
            AppOriginalDelayEntity entity5 = list.get(8);
            AppOriginalDelayEntity entity6 = list.get(15);
            double s2 = dealEntityList(entity4, entity5, entity6, qualityConfig);
            //上传速率
            AppOriginalDelayEntity entity7 = list.get(2);
            AppOriginalDelayEntity entity8 = list.get(9);
            AppOriginalDelayEntity entity9 = list.get(16);
            double s3 = dealEntityList(entity7, entity8, entity9, qualityConfig);
            //下载速率
            AppOriginalDelayEntity entity10 = list.get(3);
            AppOriginalDelayEntity entity11 = list.get(10);
            AppOriginalDelayEntity entity12 = list.get(17);
            double s4 = dealEntityList(entity10, entity11, entity12, qualityConfig);
            //延时计算值
            delay = (s1 + s2 + s3 + s4) / 4;
            //CPU耗电量
            AppOriginalDelayEntity entity13 = list.get(4);
            AppOriginalDelayEntity entity14 = list.get(11);
            AppOriginalDelayEntity entity15 = list.get(18);
            double s5 = dealEntityList(entity13, entity14, entity15, qualityConfig);
            //CPU占用峰值
            AppOriginalDelayEntity entity16 = list.get(5);
            AppOriginalDelayEntity entity17 = list.get(12);
            AppOriginalDelayEntity entity18 = list.get(19);
            double s6 = dealEntityList(entity16, entity17, entity18, qualityConfig);
            //内存占用峰值
            AppOriginalDelayEntity entity19 = list.get(6);
            AppOriginalDelayEntity entity20 = list.get(13);
            AppOriginalDelayEntity entity21 = list.get(20);
            double s7 = dealEntityList(entity19, entity20, entity21, qualityConfig);
            //功耗计算值
            consume = (s5 + s6 + s7) / 3;
        }else if(list.size()==18){
            //启动时延
            AppOriginalDelayEntity entity1 = list.get(0);
            AppOriginalDelayEntity entity2 = list.get(7);
            AppOriginalDelayEntity entity3 = list.get(14);
            double s1 = dealEntityList(entity1, entity2, entity3, qualityConfig);
            //浏览时延
            AppOriginalDelayEntity entity4 = list.get(1);
            AppOriginalDelayEntity entity5 = list.get(8);
            AppOriginalDelayEntity entity6 = list.get(15);
            double s2 = dealEntityList(entity4, entity5, entity6, qualityConfig);
            //上传速率
            AppOriginalDelayEntity entity7 = list.get(2);
            AppOriginalDelayEntity entity8 = list.get(9);
            AppOriginalDelayEntity entity9 = list.get(16);
            double s3 = dealEntityList(entity7, entity8, entity9, qualityConfig);
            //下载速率
            AppOriginalDelayEntity entity10 = list.get(3);
            AppOriginalDelayEntity entity11 = list.get(10);
            AppOriginalDelayEntity entity12 = list.get(17);
            double s4 = dealEntityList(entity10, entity11, entity12, qualityConfig);
            //延时计算值
            delay = (s1 + s2 + s3 + s4) / 4;
            //CPU耗电量
            AppOriginalDelayEntity entity13 = list.get(4);
            AppOriginalDelayEntity entity14 = list.get(11);
            double s5 = dealNoWlanEntityList(entity13, entity14, qualityConfig);
            //CPU占用峰值
            AppOriginalDelayEntity entity16 = list.get(5);
            AppOriginalDelayEntity entity17 = list.get(12);
            double s6 = dealNoWlanEntityList(entity16, entity17, qualityConfig);
            //内存占用峰值
            AppOriginalDelayEntity entity19 = list.get(6);
            AppOriginalDelayEntity entity20 = list.get(13);
            double s7 = dealNoWlanEntityList(entity19, entity20, qualityConfig);
            //功耗计算值
            consume = (s5 + s6 + s7) / 3;
        }else if(list.size()==11){
            //启动时延
            AppOriginalDelayEntity entity1 = list.get(0);
            AppOriginalDelayEntity entity2 = list.get(7);
            double s1 = dealNo3gEntityList(entity1, entity2, qualityConfig);
            //浏览时延
            AppOriginalDelayEntity entity4 = list.get(1);
            AppOriginalDelayEntity entity5 = list.get(8);
            double s2 = dealNo3gEntityList(entity4, entity5, qualityConfig);
            //上传速率
            AppOriginalDelayEntity entity7 = list.get(2);
            AppOriginalDelayEntity entity8 = list.get(9);
            double s3 = dealNo3gEntityList(entity7, entity8, qualityConfig);
            //下载速率
            AppOriginalDelayEntity entity10 = list.get(3);
            AppOriginalDelayEntity entity11 = list.get(10);
            double s4 = dealNo3gEntityList(entity10, entity11, qualityConfig);
            //延时计算值
            delay = (s1 + s2 + s3 + s4) / 4;
            //CPU耗电量
            AppOriginalDelayEntity entity13 = list.get(4);
            double s5 = dealEntity(entity13)*qualityConfig.getW4g();
            //CPU占用峰值
            AppOriginalDelayEntity entity16 = list.get(5);
            double s6 = dealEntity(entity16)*qualityConfig.getW4g();
            //内存占用峰值
            AppOriginalDelayEntity entity19 = list.get(6);
            double s7 = dealEntity(entity19)*qualityConfig.getW4g();
            //功耗计算值
            consume = (s5 + s6 + s7) / 3;
        }else if(list.size()==30){
            //客户端启动时延(s)
            AppOriginalDelayEntity entity1 = list.get(0);
            AppOriginalDelayEntity entity2 = list.get(10);
            AppOriginalDelayEntity entity3 = list.get(20);
            double s1 = dealEntityList(entity1, entity2, entity3, qualityConfig);
            //直播播放等待时延(s)
            AppOriginalDelayEntity entity4 = list.get(1);
            AppOriginalDelayEntity entity5 = list.get(11);
            AppOriginalDelayEntity entity6 = list.get(21);
            double s2 = dealEntityList(entity4, entity5, entity6, qualityConfig);
            //小视频播放等待时延(s)
            AppOriginalDelayEntity entity7 = list.get(2);
            AppOriginalDelayEntity entity8 = list.get(12);
            AppOriginalDelayEntity entity9 = list.get(22);
            double s3 = dealEntityList(entity7, entity8, entity9, qualityConfig);
            //直播在线播放5分钟卡顿次数(s)
            AppOriginalDelayEntity entity10 = list.get(3);
            AppOriginalDelayEntity entity11 = list.get(13);
            AppOriginalDelayEntity entity12 = list.get(23);
            double s4 = dealEntityList(entity10, entity11, entity12, qualityConfig);
            //延时计算值
            delay = (s1 + s2 + s3 + s4) / 4;
            //直播视频在线播放时CPU电量消耗(J)
            AppOriginalDelayEntity entity13 = list.get(4);
            AppOriginalDelayEntity entity14 = list.get(14);
            AppOriginalDelayEntity entity15 = list.get(24);
            double s5 = dealEntityList(entity13, entity14, entity15, qualityConfig);
            //直播视频在线播放CPU占用峰值(%)
            AppOriginalDelayEntity entity16 = list.get(5);
            AppOriginalDelayEntity entity17 = list.get(15);
            AppOriginalDelayEntity entity18 = list.get(25);
            double s6 = dealEntityList(entity16, entity17, entity18, qualityConfig);
            //直播视频在线播放时内存占用峰值(MB)
            AppOriginalDelayEntity entity19 = list.get(6);
            AppOriginalDelayEntity entity20 = list.get(16);
            AppOriginalDelayEntity entity21 = list.get(26);
            double s7 = dealEntityList(entity19, entity20, entity21, qualityConfig);
            //小视频在线播放时CPU电量消耗(J）
            AppOriginalDelayEntity entity22 = list.get(7);
            AppOriginalDelayEntity entity23 = list.get(17);
            AppOriginalDelayEntity entity24 = list.get(27);
            double s8 = dealEntityList(entity22, entity23, entity24, qualityConfig);
            //小视频在线播放CPU占用峰值(%)
            AppOriginalDelayEntity entity25 = list.get(8);
            AppOriginalDelayEntity entity26 = list.get(18);
            AppOriginalDelayEntity entity27 = list.get(28);
            double s9 = dealEntityList(entity25, entity26, entity27, qualityConfig);
            //小视频在线播放时内存占用峰值(MB)
            AppOriginalDelayEntity entity28 = list.get(9);
            AppOriginalDelayEntity entity29 = list.get(19);
            AppOriginalDelayEntity entity30 = list.get(29);
            double s10 = dealEntityList(entity28, entity29, entity30, qualityConfig);
            //功耗计算值
            consume = (s5 + s6 + s7 + s8 + s9 + s10) / 6;
        }
        //double格式化
        double delayDF = Double.valueOf(DF.format(delay));
        double consumeDF = Double.valueOf(DF.format(consume));
        //补全数据
        AppCalculationQualityEntity entity = new AppCalculationQualityEntity(0, list.get(0).getApp(), list.get(0).getCategory(), 0.0, 0.0, delayDF, consumeDF, 0.0, "", 0, list.get(0).getMonth(), new Date());
        int i = delayDao.saveDelay(entity);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    private double dealEntityList(AppOriginalDelayEntity entity1, AppOriginalDelayEntity entity2, AppOriginalDelayEntity entity3, AppWeightQualityEntity config) {
        double s1 = dealEntity(entity1) * config.getW3g();
        double s2 = dealEntity(entity2) * config.getW4g();
        double s3 = dealEntity(entity3) * config.getWwlan();
        double s = s1 + s2 + s3;
        return s;
    }

    /**
     * 只有4g和wlan时求平均值
     * @param entity1
     * @param entity2
     * @param config
     * @return
     */
    private double dealNo3gEntityList(AppOriginalDelayEntity entity1, AppOriginalDelayEntity entity2, AppWeightQualityEntity config) {
        double s1 = dealEntity(entity1) * config.getW4g();
        double s2 = dealEntity(entity2) * config.getWwlan();
        double s = s1 + s2 ;
        return s;
    }
    /**
     * 只有3g和4g时求平均值
     * @param entity1
     * @param entity2
     * @param config
     * @return
     */
    private double dealNoWlanEntityList(AppOriginalDelayEntity entity1, AppOriginalDelayEntity entity2, AppWeightQualityEntity config) {
        double s1 = dealEntity(entity1) * config.getW3g();
        double s2 = dealEntity(entity2) * config.getW4g();
        double s = s1 + s2 ;
        return s;
    }

    private double dealEntity(AppOriginalDelayEntity entity) {
        double sx=0;
        //测量值
        double x = entity.getMeasure();
        //挑战值
        double a = entity.getChallenge();
        //达标值
        double b = entity.getStandard();
        if (entity.getMeasuring().contains("速率")){
            if (x>b){
                sx=100;
            }else if (a<x&&x<b){
                sx=(b - x) / (b - a) * 40 + 60;
            }else if (a/2<x&&x<a){
                sx=60;
            }else if (x<a/2){
                sx=0;
            }
        }else {
            if (x>2*a){
                sx=0;
            }else if (a<x&&x<2*a){
                sx=60;
            }else if (b<x&&x<a){
                sx=(b - x) / (b - a) * 40 + 60;
            }else if (x<b){
                sx=100;
            }
        }
        return sx;
    }

    @Transactional
    @Override
    public String readExcelFile(MultipartFile file) {
        try {
            String name = file.getOriginalFilename();
            Workbook sheets;
            if (name.contains("xlsx")) {
                sheets = new XSSFWorkbook(file.getInputStream());
            } else {
                sheets = new HSSFWorkbook(file.getInputStream());
            }
            // 得到第一个shell
            Sheet sheet = sheets.getSheetAt(0);
            // 得到Excel的行数和列数
            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            List<AppOriginalDelayEntity> list = new ArrayList<>();
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // 循环Excel的列
                AppOriginalDelayEntity entity = new AppOriginalDelayEntity();
                for (int c = 0; c < totalCells; c++) {
                    Cell cell = row.getCell(c);
                    if (null != cell) {
                        if (c == 0) {
                            entity.setMonth(cell.getStringCellValue());
                        } else if (c == 1) {
                            String categoryName = cell.getStringCellValue();
                            entity.setCategory(excelDao.findIdByCategoryName(categoryName));
                        } else if (c == 2) {
                            String appName = cell.getStringCellValue();
                            entity.setApp(excelDao.findIdByAppName(appName));
                        } else if (c == 3) {
                            entity.setVersion(cell.getStringCellValue());
                        } else if (c == 4) {
                            continue;
                        } else if (c == 5) {
                            entity.setMeasuring(cell.getStringCellValue());
                        } else if (c == 6) {
                            entity.setNetwork(cell.getStringCellValue());
                        } else if (c == 7) {
                            entity.setMeasure(cell.getNumericCellValue());
                        } else if (c == 8) {
                            entity.setStandard(cell.getNumericCellValue());
                        } else if (c == 9) {
                            entity.setChallenge(cell.getNumericCellValue());
                        }
                    }
                }
                // 添加到list
                list.add(entity);
            }
            //处理中间值，导出excel
            List score=new ArrayList();
            for (AppOriginalDelayEntity entity : list) {
                double v = dealEntity(entity);
                score.add(v);
            }
            exportDelayExcel(list,score,name);
            //app+version组成的字符串来去重
            Set<String> set=new HashSet();
            for(AppOriginalDelayEntity entity:list){
                String key=entity.getApp()+""+entity.getVersion();
                set.add(key);
            }
            //遍历set，放到map中
            Map<String,List> map=new HashMap();
            for (String s:set){
                map.put(s,new ArrayList<AppOriginalDelayEntity>());
            }
            for(AppOriginalDelayEntity entity:list){
                String key=entity.getApp()+""+entity.getVersion();
                map.get(key).add(entity);
            }
            for (List<AppOriginalDelayEntity> list1:map.values()){
                boolean b1 = saveAppOriginalDelayList(list1);
                boolean b2 = dealAppOriginalDelayList(list1);
                if (!b1||!b2) {
                    //手动回滚事物
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return "失败";
                }
            }
            return "成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 导出时延功耗的excel
     * @param entityList
     * @param score
     * @param name
     */
    private void exportDelayExcel(List<AppOriginalDelayEntity> entityList,List<Double> score,String name) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("sheet1");
        XSSFRow row1=sheet.createRow(0);
        //创建单元格并设置单元格内容
        row1.createCell(0).setCellValue("测评阶段");
        row1.createCell(1).setCellValue("产品类别");
        row1.createCell(2).setCellValue("产品名称");
        row1.createCell(3).setCellValue("产品版本");
        row1.createCell(4).setCellValue("测量指标");
        row1.createCell(5).setCellValue("网络环境");
        row1.createCell(6).setCellValue("测量值");
        row1.createCell(7).setCellValue("达标值");
        row1.createCell(8).setCellValue("挑战值");
        row1.createCell(9).setCellValue("中间值");
        int i=1;
        for (AppOriginalDelayEntity entity : entityList) {
            XSSFRow row=sheet.createRow(i);
            row.createCell(0).setCellValue(entity.getMonth());
            row.createCell(1).setCellValue(excelDao.findCategoryNameById(entity.getCategory()));
            row.createCell(2).setCellValue(excelDao.findAppNameById(entity.getApp()));
            row.createCell(3).setCellValue(entity.getVersion());
            row.createCell(4).setCellValue(entity.getMeasuring());
            row.createCell(5).setCellValue(entity.getNetwork());
            row.createCell(6).setCellValue(entity.getMeasure());
            row.createCell(7).setCellValue(entity.getStandard());
            row.createCell(8).setCellValue(entity.getChallenge());
            row.createCell(9).setCellValue(score.get(i-1));
            i++;
        }
        //输出Excel文件
        try {
            File file=new File("D:/时延功耗");
            if(!file.exists()){
                file.mkdir();
            }
            String fileName = name.substring(0, name.lastIndexOf("."));
            wb.write(new FileOutputStream(new File("D:/时延功耗/"+fileName+".xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
