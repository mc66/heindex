package com.cmri.um.he.index.operations.service;

import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 处理app运营体验得分
 * @author machao
 * Created on 2018/7/11
 */
public interface AppOperationExperienceService {

    /**
     * 查询表中是否有当前数据
     * @param appCalculationOperationsEntity
     * @return
     */
    AppCalculationOperationsEntity find(AppCalculationOperationsEntity appCalculationOperationsEntity);

    /**
     * 表中已有的数据将得分存入
     * @param id
     * @param experience
     */
    void update(int id,double experience);

    /**
     * 表中没有添加一条数据
     * @param appCalculationOperationsEntity
     */
    void add(AppCalculationOperationsEntity appCalculationOperationsEntity);

    /**
     * 读取excel中的数据,生成list
     */
    String readExcelFile(MultipartFile file);

}
