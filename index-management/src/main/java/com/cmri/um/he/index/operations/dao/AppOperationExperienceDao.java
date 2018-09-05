package com.cmri.um.he.index.operations.dao;

import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.mapper.AppOperationExperienceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 处理app运营体验得分
 * @author machao
 * Created on 2018/7/11
 */
@Repository
public class AppOperationExperienceDao extends BaseDao{

    @Autowired
    AppOperationExperienceMapper appOperationExperienceMapper;

    /**
     * 查询表中是否有当前数据
     * @param month
     * @param app
     * @param version
     * @return
     */
    public AppCalculationOperationsEntity find(String month,Integer app,String version) {
        return appOperationExperienceMapper.find(month,app,version);
    }

    /**
     * 将已有数据的运营得分存入表中
     * @param id
     * @param experience
     */
    public void update(int id,double experience){
        appOperationExperienceMapper.update(id,experience);
    }

    /**
     * 将数据添加到表中
     * @param appCalculationOperationsEntity
     */
    public void add(AppCalculationOperationsEntity appCalculationOperationsEntity){
        appOperationExperienceMapper.add(appCalculationOperationsEntity);
    }

    public int updateExperience(AppCalculationOperationsEntity appCalculationOperationsEntity){
        return appOperationExperienceMapper.updateExperience(appCalculationOperationsEntity);
    }
}
