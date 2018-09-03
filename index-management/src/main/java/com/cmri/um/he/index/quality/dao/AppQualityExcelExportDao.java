package com.cmri.um.he.index.quality.dao;

import com.cmri.um.he.index.quality.mapper.AppQualityExcelExportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lch
 * Created on 2018/08/31 16:49
 */
@Repository
public class AppQualityExcelExportDao {
    @Autowired
    private AppQualityExcelExportMapper mapper;

    public List<Map<String,Object>> getAllCalculationQuality(){
        return mapper.getAllCalculationQuality();
    }
}
