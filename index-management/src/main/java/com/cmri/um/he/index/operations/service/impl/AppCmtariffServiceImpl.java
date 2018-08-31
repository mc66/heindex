package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppCmtariffDao;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.entity.AppOriginalOperationsEntity;
import com.cmri.um.he.index.operations.service.AppCmtariffService;
import com.cmri.um.he.index.quality.dao.AppExcelDao;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 渠道、营销、资费、服务原始数据
 *
 * @author limin
 * Created on 2018/7/10
 */
@Service
public class AppCmtariffServiceImpl implements AppCmtariffService {

    @Autowired
    private AppCmtariffDao appCmtariffDao;
    @Autowired
    private AppExcelDao excelDao;

    private final static DecimalFormat DF = new DecimalFormat("####0.00");

    @Override
    public boolean saveAppOriginalContentEntity(List<AppOriginalOperationsEntity> list) {

        for (AppOriginalOperationsEntity entity : list) {
            entity.setAtime(new Date());
            appCmtariffDao.saveAppOriginalContentEntity(entity);
        }
        return true;
    }

    @Override
    public boolean updateAppOriginalContentEntity(List<AppOriginalOperationsEntity> list) {
        int app = list.get(0).getApp();
        String month = list.get(0).getMonth();
        Set<String> set=new HashSet();
        for (AppOriginalOperationsEntity appOriginalOperationsEntity : list) {
            set.add(appOriginalOperationsEntity.getDimensions());
        }
        double v1=0;
        double v2=0;
        double v3=0;
        double v4=0;
        for (String dimensions : set) {
            if (dimensions.equals("服务")){
                int bars1 = appCmtariffDao.queryAppCalculationOperationsEntityByMeasureValue("服务",app,month);
                int total1 = appCmtariffDao.queryAppCalculationOperationsEntityByDimensionsId("服务",app,month);
                v1 = (double) bars1 / total1;
            }else if (dimensions.equals("渠道")){
                int bars2 = appCmtariffDao.queryAppCalculationOperationsEntityByMeasureValue("渠道",app,month);
                int total2 = appCmtariffDao.queryAppCalculationOperationsEntityByDimensionsId("渠道",app,month);
                v2 = (double) bars2 / total2;
            }else if (dimensions.equals("营销")){
                int bars3 = appCmtariffDao.queryAppCalculationOperationsEntityByMeasureValue("营销",app,month);
                int total3 = appCmtariffDao.queryAppCalculationOperationsEntityByDimensionsId("营销",app,month);
                v3 = (double) bars3 / total3;
            }else if (dimensions.equals("资费")){
                int bars4 = appCmtariffDao.queryAppCalculationOperationsEntityByMeasureValue("资费",app,month);
                int total4 = appCmtariffDao.queryAppCalculationOperationsEntityByDimensionsId("资费",app,month);
                v4 = (double) bars4 / total4;
            }
        }
        AppCalculationOperationsEntity entity=new AppCalculationOperationsEntity();
        entity.setApp(app);
        entity.setMonth(month);
        entity.setService(Double.valueOf(DF.format(v1)));
        entity.setChannel(Double.valueOf(DF.format(v2)));
        entity.setMarket(Double.valueOf(DF.format(v3)));
        entity.setTariff(Double.valueOf(DF.format(v4)));
        boolean b = appCmtariffDao.updateAppCalculationOperations(entity.getChannel(), entity.getMarket(), entity.getService(), entity.getTariff(), entity.getApp(), entity.getMonth());
        return b;
    }

    @Override
    @Transactional
    public String readExcelFile(List<MultipartFile> files) {
        List<AppOriginalOperationsEntity> list = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.getOriginalFilename().contains("渠道")) {
                List list1 = readQudaoExcelFile(file, "渠道");
                list.addAll(list1);
            } else if (file.getOriginalFilename().contains("服务")) {
                List list1 = readFuwuExcelFile(file, "服务");
                list.addAll(list1);
            } else if (file.getOriginalFilename().contains("营销")) {
                List list1 = readYingxiaoZifeiExcelFile(file, "营销");
                list.addAll(list1);
            } else if (file.getOriginalFilename().contains("资费")) {
                List list1 = readYingxiaoZifeiExcelFile(file, "资费");
                list.addAll(list1);
            }
        }
        //list根据app分类
        Set set = new HashSet();
        for (AppOriginalOperationsEntity entity : list) {
            set.add(entity.getApp());
        }
        Map<Object, List<AppOriginalOperationsEntity>> map = new HashMap();
        for (Object o : set) {
            map.put(o, new ArrayList<>());
        }
        for (AppOriginalOperationsEntity entity : list) {
            int key = entity.getApp();
            map.get(key).add(entity);
        }
        for (List<AppOriginalOperationsEntity> entityList : map.values()) {
            boolean b1 = saveAppOriginalContentEntity(entityList);
            boolean b2 = updateAppOriginalContentEntity(entityList);
            if (!b1 || !b2) {
                return "失败!";
            }
        }
        return "成功!";

    }

    private List readQudaoExcelFile(MultipartFile file, String dimensions) {
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
            List<AppOriginalOperationsEntity> list = new ArrayList<>();
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // 循环Excel的列
                AppOriginalOperationsEntity entity = new AppOriginalOperationsEntity();
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
                            entity.setMeasureIndex(cell.getStringCellValue());
                        } else if (c == 6) {
                            entity.setSpecificChannel(cell.getStringCellValue());
                        } else if (c == 7) {
                            entity.setMeasureValue((int) cell.getNumericCellValue());
                        } else if (c == 8) {
                            entity.setExplain(cell.getStringCellValue());
                        }
                    }
                }
                entity.setDimensions(dimensions);
                // 添加到list
                list.add(entity);
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List readFuwuExcelFile(MultipartFile file, String dimensions) {
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
            List<AppOriginalOperationsEntity> list = new ArrayList<>();
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // 循环Excel的列
                AppOriginalOperationsEntity entity = new AppOriginalOperationsEntity();
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
                            entity.setMeasureIndex(cell.getStringCellValue());
                        } else if (c == 6) {
                            entity.setServerFrom(cell.getStringCellValue());
                        } else if (c == 7) {
                            entity.setMeasureValue((int) cell.getNumericCellValue());
                        } else if (c == 8) {
                            entity.setExplain(cell.getStringCellValue());
                        }
                    }
                }
                entity.setDimensions(dimensions);
                // 添加到list
                list.add(entity);
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List readYingxiaoZifeiExcelFile(MultipartFile file, String dimensions) {
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
            List<AppOriginalOperationsEntity> list = new ArrayList<>();
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // 循环Excel的列
                AppOriginalOperationsEntity entity = new AppOriginalOperationsEntity();
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
                            entity.setMeasureIndex(cell.getStringCellValue());
                        } else if (c == 6) {
                            entity.setMeasureValue((int) cell.getNumericCellValue());
                        } else if (c == 7) {
                            if (cell.getCellTypeEnum().equals(CellType.STRING)) {
                                entity.setExplain(cell.getStringCellValue());
                            } else {
                                entity.setExplain(String.valueOf((int) cell.getNumericCellValue()));
                            }

                        }
                    }
                }
                entity.setDimensions(dimensions);
                // 添加到list
                list.add(entity);
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
