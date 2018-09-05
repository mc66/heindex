package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppCalculationOperationsDao;
import com.cmri.um.he.index.operations.dao.AppOriginalContentDao;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;
import com.cmri.um.he.index.operations.service.AppOriginalContentService;
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
import java.math.BigDecimal;
import java.util.*;

/**
 * 运营指数中内容得分
 * @author shihao
 * Created on 2018/7/12
 */
@Service
public class AppOriginalContentServiceImpl implements AppOriginalContentService {

    @Autowired
    private AppOriginalContentDao appOriginalContentDao;

    @Autowired
    private AppCalculationOperationsDao appCalculationOperationsDao;
    @Autowired
    private AppExcelDao excelDao;

    /**
     * 查询内容的分类
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getContent() {
        List<Map<String, Object>> list = appOriginalContentDao.getContent();
        return list;
    }

    @Override
    @Transactional
    public String readExcelFile(List<MultipartFile> files) {
        List<AppOriginalContentEntity> list=new ArrayList<>();
        for (MultipartFile file:files){
            if (file.getOriginalFilename().contains("覆盖")){
                List list1 = readFugaiGengxinExcelFile(file,1);
                list.addAll(list1);
            }else if(file.getOriginalFilename().contains("更新")){
                List list1 = readFugaiGengxinExcelFile(file,2);
                list.addAll(list1);
            }else if(file.getOriginalFilename().contains("推荐")){
                List list1 = readTuijianExcelFile(file);
                list.addAll(list1);
            }
        }
        //list根据app分类
        Set set=new HashSet();
        for (AppOriginalContentEntity entity : list) {
            set.add(entity.getApp());
        }
        Map<Object,List<AppOriginalContentEntity>> map=new HashMap();
        for (Object o : set) {
            map.put(o,new ArrayList<>());
        }
        for (AppOriginalContentEntity entity : list) {
            int key = entity.getApp();
            map.get(key).add(entity);
        }
        for (List<AppOriginalContentEntity> entityList : map.values()) {
            boolean b = saveContent(entityList);
            if (!b){
                //手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return "失败!";
            }
        }
        return "成功!";
    }

    private List readFugaiGengxinExcelFile(MultipartFile file,int contentId) {
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
            List<AppOriginalContentEntity> list = new ArrayList<>();
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // 循环Excel的列
                AppOriginalContentEntity entity = new AppOriginalContentEntity();
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
                            entity.setContentName(cell.getStringCellValue());
                        } else if (c == 6) {
                            entity.setMeasuredValue((int)cell.getNumericCellValue());
                        } else if (c == 7) {
                            entity.setRemarks(cell.getStringCellValue());
                        }
                    }
                }
                entity.setContentId(contentId);
                // 添加到list
                list.add(entity);
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List readTuijianExcelFile(MultipartFile file) {
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
            List<AppOriginalContentEntity> list = new ArrayList<>();
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // 循环Excel的列
                AppOriginalContentEntity entity = new AppOriginalContentEntity();
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
                            entity.setMeasuredIndex(cell.getStringCellValue());
                        } else if (c == 6) {
                            entity.setMeasuredProblem(cell.getStringCellValue());
                        } else if (c == 7) {
                            entity.setMeasuredValue((int) cell.getNumericCellValue());
                        }
                    }
                }
                entity.setContentId(3);
                // 添加到list
                list.add(entity);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveContent(List<AppOriginalContentEntity> list) {

        int app = 0;
        String month = "";
        int category = 0;
        for (AppOriginalContentEntity appOriginalContentEntity : list) {
            app = appOriginalContentEntity.getApp();
            category = appOriginalContentEntity.getCategory();
            month = appOriginalContentEntity.getMonth();
            List<Map<String, Object>> list2 = appOriginalContentDao.getContent();
            for (int i = 0; i < list2.size(); i++) {
                int id = (int) list2.get(i).get("id");
                if (appOriginalContentEntity.getContentId() == id) {
                    appOriginalContentEntity.setAtime(new Date());
                    //保存内容数据
                    appOriginalContentDao.saveContent(appOriginalContentEntity);
                }
            }
        }
        List list3 = new ArrayList();
        List<Map<String, Object>> list4 = appOriginalContentDao.getContentByapp(app,month);
        for (int i = 0; i < list4.size(); i++) {
            //查得样本库大小
            int contentId = (int) list4.get(i).get("id");
            int count1 = appOriginalContentDao.getContentByContentId(contentId, app,month);
            //查得测量值为1的数据数
            int count2 = appOriginalContentDao.getContentByContentIdAndMeasureValue(contentId, 1, app,month);
            //计算得分
            if (count1 == 0) {
                double sorce = 0.0;
                list3.add(sorce);
            } else {
                double sorce = new BigDecimal((float) count2 / count1 * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                list3.add(sorce);
            }
        }

        int num=list4.size();
        double content = 0.0;
        double contents = 0.0;
        for (int i = 0; i < list3.size(); i++) {
            contents += (double) list3.get(i);
        }
        content = new BigDecimal((float) contents / num).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //封装数据
        AppCalculationOperationsEntity appCalculationOperationsEntity = new AppCalculationOperationsEntity();
        appCalculationOperationsEntity.setCategory(category);
        appCalculationOperationsEntity.setApp(app);
        appCalculationOperationsEntity.setMonth(month);
        appCalculationOperationsEntity.setContent(content);
        appCalculationOperationsEntity.setStatus(0);
        appCalculationOperationsEntity.setAtime(new Date());
        //根据app查询是否存在内容得分
        boolean b = false;
        String contentValue = appCalculationOperationsDao.getDataByApp(app, month);
        if (contentValue == null) {
            //保存得分
            int number = appCalculationOperationsDao.saveContentValue(appCalculationOperationsEntity);
            if (number > 0) {
                b = true;
            }
        } else if (contentValue != null) {
            //修改得分
            int number = appCalculationOperationsDao.updateContentValue(content, app, month);
            if (number > 0) {
                b = true;
            }
        }
        return b;
    }
}
