package com.cmri.um.he.index.quality.dao;

import com.cmri.um.he.index.quality.mapper.AppExcelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author lch
 * Created on 2018/08/13 11:10
 */
@Repository
public class AppExcelDao extends BaseDao {
    @Autowired
    AppExcelMapper excelMapper;

    public int findIdByCategoryName(String categoryName) {
        return excelMapper.findIdByCategoryName(categoryName);
    }

    public int findIdByAppName(String appName) {
        return excelMapper.findIdByAppName(appName);
    }

    public String findCategoryNameById(int categoryId) {
        return excelMapper.findCategoryNameById(categoryId);
    }

    public String findAppNameById(int appId) {
        return excelMapper.findAppNameById(appId);
    }
}
