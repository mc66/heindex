package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.AppExcelDao;
import com.cmri.um.he.index.quality.dao.AppOriginalExperiencDao;
import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppOriginalExperienceEntity;
import com.cmri.um.he.index.quality.service.AppOriginalExperienceService;
import com.cmri.um.he.index.quality.service.AppQualityService;
import com.cmri.um.he.index.quality.service.StandardService;
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
import java.util.List;
import java.util.Map;
/**
 * 查询某类未添加应用体验接口的服务类的服务类实现
 * @author limin
 * Created on 2018/6/26
 */
@Service
public class AppOriginalExperienceServiceImpl implements AppOriginalExperienceService {

    @Autowired
    private AppOriginalExperiencDao appOriginalExperiencDao;
    @Autowired
    private AppExcelDao excelDao;
    @Autowired
    private StandardService standardService;
    @Autowired
    AppQualityService appQualityService;

    @Override
    public List<Map<String, Object>> findWhole() {
        return appOriginalExperiencDao.findWhole();
    }

    @Override
    public List<Map<String, Object>> find(  Integer category) {

        return appOriginalExperiencDao.find( category);
    }

    @Override
    public int updateExperience(Integer id, Double experience) {
        return appOriginalExperiencDao.updateExperience(id,experience);
    }

    @Override
    public String updateExperienceAll(List<AppOriginalExperienceEntity> list) {
        for (AppOriginalExperienceEntity experie :list) {
            int n= appOriginalExperiencDao.updateExperienceAll(experie);
            if (n > 1){
                return "成功";
            }
        }
        return "失败";
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
            List<AppOriginalExperienceEntity> list = new ArrayList<>();
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // 循环Excel的列
                AppOriginalExperienceEntity entity = new AppOriginalExperienceEntity();
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
            updateExperienceAll(list);
            //计算值转标准值
            List<AppCalculationQualityEntity> list1 =standardService.unprocessedStandard(list.get(0).getCategory(), list.get(0).getMonth());
            standardService.standard(list1);
            //计算应用的品质总分
            List<Map<String, Object>> quary = appQualityService.quary();
            boolean b = appQualityService.setQindex(quary);
            if (!b){
                //手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return "失败!";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "成功!";
    }
}
