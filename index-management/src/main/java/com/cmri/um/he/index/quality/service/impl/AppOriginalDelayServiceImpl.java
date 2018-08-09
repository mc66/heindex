package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.AppOriginalDelayDao;
import com.cmri.um.he.index.quality.dao.AppQualityDao;
import com.cmri.um.he.index.quality.dao.AppWeightQualityDao;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppOriginalDelayEntity;
import com.cmri.um.he.index.quality.entity.AppWeightQualityEntity;
import com.cmri.um.he.index.quality.service.AppOriginalDelayService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private AppQualityDao qualityDao;

    private final static DecimalFormat DF=new DecimalFormat("####0.00");

    @Override
    public List<Map<String, Object>> findAppByCategory(Integer category) {
        return delayDao.findAppByCategory(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAppOriginalDelayList(List<AppOriginalDelayEntity> list) {
        Date date=new Date();
        for (AppOriginalDelayEntity entity:list) {
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
        int category=list.get(0).getCategory();
        AppWeightQualityEntity qualityConfig = weightQualityDao.findQualityConfig(category);

        //启动时延
        AppOriginalDelayEntity entity1=list.get(0);
        AppOriginalDelayEntity entity2=list.get(1);
        AppOriginalDelayEntity entity3=list.get(2);
        double s1 = dealEntityList(entity1, entity2, entity3,qualityConfig);
        //浏览时延
        AppOriginalDelayEntity entity4=list.get(3);
        AppOriginalDelayEntity entity5=list.get(4);
        AppOriginalDelayEntity entity6=list.get(5);
        double s2 = dealEntityList(entity4, entity5, entity6,qualityConfig);
        //上传速率
        AppOriginalDelayEntity entity7=list.get(6);
        AppOriginalDelayEntity entity8=list.get(7);
        AppOriginalDelayEntity entity9=list.get(8);
        double s3 = dealEntityList(entity7, entity8, entity9,qualityConfig);
        //下载速率
        AppOriginalDelayEntity entity10=list.get(9);
        AppOriginalDelayEntity entity11=list.get(10);
        AppOriginalDelayEntity entity12=list.get(11);
        double s4 = dealEntityList(entity10, entity11, entity12,qualityConfig);
        //延时计算值
        double delay=(s1+s2+s3+s4)/4;
        //CPU耗电量
        AppOriginalDelayEntity entity13=list.get(12);
        AppOriginalDelayEntity entity14=list.get(13);
        AppOriginalDelayEntity entity15=list.get(14);
        double s5 = dealEntityList(entity13, entity14, entity15,qualityConfig);
        //CPU占用峰值
        AppOriginalDelayEntity entity16=list.get(15);
        AppOriginalDelayEntity entity17=list.get(16);
        AppOriginalDelayEntity entity18=list.get(17);
        double s6 = dealEntityList(entity16, entity17, entity18,qualityConfig);
        //内存占用峰值
        AppOriginalDelayEntity entity19=list.get(18);
        AppOriginalDelayEntity entity20=list.get(19);
        AppOriginalDelayEntity entity21=list.get(20);
        double s7 = dealEntityList(entity19, entity20, entity21,qualityConfig);
        //功耗计算值
        double consume=(s5+s6+s7)/3;
        //double格式化
        double delayDF=Double.valueOf(DF.format(delay));
        double consumeDF=Double.valueOf(DF.format(consume));
        //补全数据
        AppCalculationQualityEntity entity=new AppCalculationQualityEntity(0,0,entity1.getApp(),entity1.getCategory(),0.0,0.0,delayDF,consumeDF,0.0,entity1.getVersion(),0,entity1.getMonth(),new Date());
        int i = delayDao.saveDelay(entity);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    private double dealEntityList(AppOriginalDelayEntity entity1,AppOriginalDelayEntity entity2,AppOriginalDelayEntity entity3,AppWeightQualityEntity config){
        double s1 = dealEntity(entity1)*config.getW3g();
        double s2 = dealEntity(entity2)*config.getW4g();
        double s3 = dealEntity(entity3)*config.getWwlan();
        double s=(s1+s2+s3)/3;
        return s;
    }

    private double dealEntity(AppOriginalDelayEntity entity){
        //测量值
        double x=entity.getMeasure();
        //挑战值
        double a=entity.getChallenge();
        //达标值
        double b=entity.getStandard();
        double sx=(b-x)/(b-a)*40+60;
        return sx;
    }

    @Transactional
    @Override
    public String readExcelFile(MultipartFile file) {
        try {
            HSSFWorkbook sheets = new HSSFWorkbook(file.getInputStream());
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
                            entity.setCategory(qualityDao.findIdByCategoryName(categoryName));
                        } else if (c == 2) {
                            String appName = cell.getStringCellValue();
                            entity.setApp(qualityDao.findIdByAppName(appName));
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
            int size = list.size()/21;
            int flag=0;
            for(int i=0;i<size;i++){
                List<AppOriginalDelayEntity> newList = list.subList(i * 21, (i + 1) * 21);
                boolean b1 = saveAppOriginalDelayList(newList);
                boolean b2 = dealAppOriginalDelayList(newList);
                if (b1&&b2){
                    continue;
                }
                flag=1;
            }
            if (flag==0){
                return "成功";
            }else {
                return "失败";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
