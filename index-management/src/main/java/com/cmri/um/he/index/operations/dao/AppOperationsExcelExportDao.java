package com.cmri.um.he.index.operations.dao;

import com.cmri.um.he.index.operations.mapper.AppOperationsExcelExportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lch
 * Created on 2018/08/31 16:49
 */
@Repository
public class AppOperationsExcelExportDao {
    @Autowired
    private AppOperationsExcelExportMapper mapper;

    public List<Map<String,Object>> getAllCalculationQuality(){
        return mapper.getAllCalculationQuality();
    }
}
