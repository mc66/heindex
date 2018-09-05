package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppOperationExperienceDao;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.service.AppConversionService;
import com.cmri.um.he.index.operations.service.AppOperationExperienceService;
import com.cmri.um.he.index.quality.dao.AppExcelDao;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 处理app运营体验得分
 * @author machao
 * Created on 2018/7/11
 */
@Service
public class AppOperationExperienceServiceImpl implements AppOperationExperienceService {

    @Autowired
    AppOperationExperienceDao appOperationExperienceDao ;
    @Autowired
    private AppExcelDao excelDao;
    @Autowired
    private AppConversionService conversionService;

    @Override
    public AppCalculationOperationsEntity find(AppCalculationOperationsEntity appCalculationOperationsEntity) {
        String month = appCalculationOperationsEntity.getMonth();
        Integer app = appCalculationOperationsEntity.getApp();
        String version = appCalculationOperationsEntity.getVersion();
        return appOperationExperienceDao.find(month,app,version);
    }

    @Override
    public void update(int id,double experience) {
        appOperationExperienceDao.update(id,experience);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AppCalculationOperationsEntity appCalculationOperationsEntity) {
        Date atime = new Date();
        AppCalculationOperationsEntity acoe = new AppCalculationOperationsEntity(0, appCalculationOperationsEntity.getMonth(), appCalculationOperationsEntity.getApp(), appCalculationOperationsEntity.getCategory(), appCalculationOperationsEntity.getVersion(), 0.0, 0.0, 0.0, 0.0, 0.0, appCalculationOperationsEntity.getExperience(), 0, atime);
        appOperationExperienceDao.add(acoe);
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
            List<AppCalculationOperationsEntity> list = new ArrayList<>();
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // 循环Excel的列
                AppCalculationOperationsEntity entity = new AppCalculationOperationsEntity();
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
                            entity.setExperience(cell.getNumericCellValue());
                        }
                    }
                }
                // 添加到list
                list.add(entity);
            }
            for (AppCalculationOperationsEntity entity:list){
                int i = appOperationExperienceDao.updateExperience(entity);
                if (i==0){
                    //手动回滚事物
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return "失败!";
                }
            }
            List<AppCalculationOperationsEntity> entities = conversionService.queryUnConversion(list.get(0).getCategory(), list.get(0).getMonth());
            conversionService.saveAll(entities);
            return "成功!";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "失败!";
    }
}
