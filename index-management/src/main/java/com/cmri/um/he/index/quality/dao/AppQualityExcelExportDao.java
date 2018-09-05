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


    public List<Map<String,Object>> getAllWeight() {
        return mapper.getAllWeight();
    }

    public List<Map<String,Object>> getMonth(){
        return mapper.getMonth();
    }

    public List<Map<String,Object>> getAPP(String month){
        return mapper.getAPP(month);
    }

    public List<Map<String,Object>> getWeight(int app,String month){
        return mapper.getWeight(app,month);
    }
    public int getCount1(Integer app,String month,String dimensions){
        return mapper.getCount1(app,month,dimensions);
    }
    public int getCount2(Integer app,String month,String dimensions){
        return mapper.getCount2(app,month,dimensions);
    }
    public int getCount3(Integer app,String month,String dimensions){
        return mapper.getCount3(app,month,dimensions);

    }
}
