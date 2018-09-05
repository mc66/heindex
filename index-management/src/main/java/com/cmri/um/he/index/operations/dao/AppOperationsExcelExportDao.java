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

    /**
     * 查询当前测量时间所有app
     * @param month
     * @return
     */
    public List<Map<String,Object>> quaryAll(String month){
        return mapper.quaryAll(month);
    }

    /**
     * 查询当前app三个类别的样本数
     * @param app
     * @param month
     * @return
     */
    public Map<String,Long> quarySampleSum(Integer app,String month){
        return mapper.quarySampleSum(app,month);
    }

    /**
     * 查询当前app三个类别测量值记为1的数量
     * @param app
     * @param month
     * @return
     */
    public Map<String,Long> quarySampleNum(Integer app,String month){
        return mapper.quarySampleNum(app,month);
    }
}
