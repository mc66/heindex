package com.cmri.um.he.index.quality.dao;

import com.cmri.um.he.index.quality.mapper.AppExcelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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


    public List<Map<String, Object>> getMonth() {
        return excelMapper.getMonth();
    }

    public List<Map<String,Object>> getAPP(String month) {
        return excelMapper.getAPP(month);
    }


}
