package com.cmri.um.he.index.operations.dao;

import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.mapper.AppCalculationOperationsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * 运营指数中内容得分
 * @author shihao
 * Created on 2018/7/12
 */
@Repository
public class AppCalculationOperationsDao {

    @Autowired
    private AppCalculationOperationsMapper mapper;

    /**
     * 根据app查询是否存在内容得分
     * @param app
     * @return
     */
    public String getDataByApp(int app,String month,String version){
       String contentValue= mapper.getDataByApp(app,month,version);
       return contentValue;
    }

    /**
     * 保存得分
     * @param appCalculationOperationsEntity
     * @return
     */
    public int saveContentValue(AppCalculationOperationsEntity appCalculationOperationsEntity){
       int num= mapper.saveContentValue(appCalculationOperationsEntity);
       return num;
    }


    /**
     * 修改得分
     * @param content app
     * @return
     */
    public int updateContentValue(double content,int app,String month,String version){
        int num= mapper.updateContentValue(content,app,month,version);
        return num;
    }
}
