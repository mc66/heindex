package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.AppExcelDao;
import com.cmri.um.he.index.quality.dao.AppOrginalFeaturesDao;
import com.cmri.um.he.index.quality.dao.AppWeightQualityDao;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppOriginalFeaturesEntity;
import com.cmri.um.he.index.quality.entity.AppWeightQualityEntity;
import com.cmri.um.he.index.quality.service.AppCalculationQualityService;
import com.cmri.um.he.index.quality.service.AppOrginalFeaturesService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author shihao
 *         Created on 2018/6/24
 */
@Service
public class AppOrginalFeaturesServiceImpl implements AppOrginalFeaturesService {

    @Autowired
    private AppOrginalFeaturesDao appOrginalFeaturesDao;

    @Autowired
    private AppWeightQualityDao appWeightQualityDao;

    @Autowired
    private AppExcelDao excelDao;
    @Autowired
    private AppCalculationQualityService appCalculationQualityService;

    /**
     * 新增功能界面的原始数据
     *
     * @param list
     * @return
     */
    @Override
    @Transactional
    public boolean saveList(List<AppOriginalFeaturesEntity> list) {
        for (AppOriginalFeaturesEntity appOriginalFeaturesEntity : list) {
            appOriginalFeaturesEntity.setStatus(1);
            appOriginalFeaturesEntity.setAtime(new Date());
            appOrginalFeaturesDao.saveList(appOriginalFeaturesEntity);
        }
        return true;
    }

    /**
     * 处理添加同一app的数据
     *
     * @param list
     * @return
     */
    @Override
    @Transactional
    public AppCalculationQualityEntity getScore(List<AppOriginalFeaturesEntity> list) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getDimensions().equals("功能")) {
                String degree = list.get(i).getDegree();
                switch (degree) {
                    case "低":
                        count1++;
                        break;
                    case "中":
                        count2++;
                        break;
                    case "高":
                        count3++;
                        break;
                    default:
                        count1 = count2 = count3 = 0;
                }
            } else if (list.get(i).getDimensions().equals("界面")) {
                String degree = list.get(i).getDegree();
                switch (degree) {
                    case "低":
                        count4++;
                        break;
                    case "中":
                        count5++;
                        break;
                    case "高":
                        count6++;
                        break;
                    default:
                        count4 = count5 = count6 = 0;
                }
            }
        }
        int app = list.get(0).getApp();
        AppWeightQualityEntity appWeightQualityEntity = appWeightQualityDao.findWegihtByApp(app);

        int feaScore = 100 - (appWeightQualityEntity.getWhigh() * count3 + appWeightQualityEntity.getWmiddle() * count2 + appWeightQualityEntity.getWlow() * count1);
        int viewScore = 100 - (appWeightQualityEntity.getWhigh() * count6 + appWeightQualityEntity.getWmiddle() * count5 + appWeightQualityEntity.getWlow() * count4);

        AppCalculationQualityEntity appCalculationQualityEntity = new AppCalculationQualityEntity();
        appCalculationQualityEntity.setFeatures((double) feaScore);
        appCalculationQualityEntity.setView((double) viewScore);
        appCalculationQualityEntity.setApp(app);

        return appCalculationQualityEntity;
    }

    @Override
    @Transactional
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
            List<AppOriginalFeaturesEntity> list = new ArrayList<>();
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // 循环Excel的列
                AppOriginalFeaturesEntity entity=new AppOriginalFeaturesEntity();
                for (int c = 0; c < totalCells; c++) {
                    Cell cell = row.getCell(c);
                    if (null != cell) {
                        if (c == 0) {
                            entity.setMonth(String.valueOf(cell.getStringCellValue()));
                        } else if (c == 1) {
                            String categoryName = cell.getStringCellValue();
                            entity.setCategory(excelDao.findIdByCategoryName(categoryName));
                        } else if (c == 2) {
                            continue;
                        } else if (c == 3) {
                            String appName = cell.getStringCellValue();
                            entity.setApp(excelDao.findIdByAppName(appName));
                        } else if (c == 4) {
                            entity.setVersion(cell.getStringCellValue());
                        }else if (c == 5) {
                            entity.setDimensions(cell.getStringCellValue());
                        } else if (c == 6) {
                            entity.setDes(cell.getStringCellValue());
                        } else if (c == 7) {
                            entity.setExperience((int) cell.getNumericCellValue());
                        } else if (c == 8) {
                            entity.setFrequency((int) cell.getNumericCellValue());
                        } else if (c == 9) {
                            entity.setScore((int) cell.getNumericCellValue());
                        } else if (c == 10) {
                            entity.setDegree(cell.getStringCellValue());
                        }
                    }
                }
                // 添加到list
                list.add(entity);
            }
            //app+version组成的字符串来去重
            Set<String> set=new HashSet();
            for(AppOriginalFeaturesEntity entity:list){
                String key=entity.getApp()+""+entity.getVersion();
                set.add(key);
            }
            //遍历set，放到map中
            Map<String,List> map=new HashMap();
            for (String s:set){
                map.put(s,new ArrayList<AppOriginalFeaturesEntity>());
            }
            for(AppOriginalFeaturesEntity entity:list){
                String key=entity.getApp()+""+entity.getVersion();
                map.get(key).add(entity);
            }
            for (List<AppOriginalFeaturesEntity> list1:map.values()){
                boolean b = saveList(list1);
                AppCalculationQualityEntity entity = getScore(list1);
                int i = appCalculationQualityService.update(entity);
                if (b&&i>0){
                    continue;
                }
            }
            return "成功!";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "失败!";
    }

}
