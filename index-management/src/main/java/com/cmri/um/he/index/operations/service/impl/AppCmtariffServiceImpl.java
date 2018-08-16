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
import java.math.BigDecimal;
import java.util.*;

/**
 * 渠道、营销、资费、服务原始数据
 *
 * @author limin
 *         Created on 2018/7/10
 */
@Service
public class AppCmtariffServiceImpl implements AppCmtariffService {

    @Autowired
    private AppCmtariffDao appCmtariffDao;
    @Autowired
    private AppExcelDao excelDao;

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
        for (AppOriginalOperationsEntity ao : list) {
            int bars = appCmtariffDao.queryAppCalculationOperationsEntityByMeasureValue(ao.getDimensions());
            int total = appCmtariffDao.queryAppCalculationOperationsEntityByDimensionsId(ao.getDimensions());
            AppCalculationOperationsEntity aco = new AppCalculationOperationsEntity();
            aco.setMonth(ao.getMonth());
            aco.setApp(ao.getApp());
            aco.setAtime(new Date());
            aco.setCategory(ao.getCategory());
            aco.setStatus(0);
            aco.setVersion(ao.getVersion());
            if (total == 0) {
                double channel = 0.00;
                double tariff = 0.00;
                double ser = 0.00;
                double market = 0.00;
                aco.setChannel(channel);
                aco.setTariff(tariff);
                aco.setService(ser);
                aco.setMarket(market);
                List<Map<String, Object>> dataList = appCmtariffDao.queryAppCalculationOperations(ao.getApp(), ao.getMonth());
                if (dataList == null) {
                    appCmtariffDao.saveAppCalculationOperations(aco);
                } else {
                    appCmtariffDao.updateAppCalculationOperations(aco.getChannel(), aco.getMarket(), aco.getService(), aco.getTariff(), ao.getApp(), ao.getMonth());
                }
            } else {
                double channel = new BigDecimal((float) bars / total * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double tariff = new BigDecimal((float) bars / total * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double ser = new BigDecimal((float) bars / total * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double market = new BigDecimal((float) bars / total * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                aco.setChannel(channel);
                aco.setTariff(tariff);
                aco.setService(ser);
                aco.setMarket(market);
                List<Map<String, Object>> dataList = appCmtariffDao.queryAppCalculationOperations(ao.getApp(), ao.getMonth());
                if (dataList.size() == 0) {
                    appCmtariffDao.saveAppCalculationOperations(aco);
                } else {
                    appCmtariffDao.updateAppCalculationOperations(aco.getChannel(), aco.getMarket(), aco.getService(), aco.getTariff(), ao.getApp(), ao.getMonth());
                }
            }

        }

        return true;
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
