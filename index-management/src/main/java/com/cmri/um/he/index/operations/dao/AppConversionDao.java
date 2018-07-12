package com.cmri.um.he.index.operations.dao;

import com.cmri.um.he.index.operations.entity.AppOperationsEntity;
import com.cmri.um.he.index.operations.mapper.AppConversionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 转换标准值的数据库访问
 *
 * @author yinjunjun
 * Created on 2018/07/10
 */
@Repository
public class AppConversionDao{

    @Autowired
    private AppConversionMapper mapper;

    /**
     * 根据月份和类别查询还没有处理的数据
     * */
    public List<Map<String,Object>> queryUnConversion(Integer category,String monrh){
        return mapper.queryUnConversion(category, monrh);
    }

    /**
     * 转换之后批量添加数据
     * */
    public void saveAll(List<AppOperationsEntity> appOperationsEntityList){
        mapper.saveAll(appOperationsEntityList);
    }

    /**
     * 转换之后改变状态
     * */
    public void changeStatus(Integer category,String month){
        mapper.UpdateStatus(category, month);
    }

    /**
     * 查询所有报告期数
     * */
    public List<Map<String,Object>> findMonths(){
        return mapper.findMonths();
    }

    public List<Map<String,Object>> ceshi(){
       return mapper.ceshi();
    }
}
