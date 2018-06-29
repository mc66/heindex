package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.entity.AppOriginalFeaturesEntity;
import com.cmri.um.he.index.mapper.AppFeaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author shihao
 * Created on 2018/6/24
 */
@Repository
public class AppOrginalFeaturesDao extends BaseDao {
    @Autowired
    private AppFeaMapper mapper;

    /**
     * 新增功能界面的原始数据
     * @param appOriginalFeaturesEntity
     * @return
     */
    public boolean saveList(AppOriginalFeaturesEntity appOriginalFeaturesEntity){
        int i=  mapper.saveList(appOriginalFeaturesEntity);
        if (i>0){
            return true;
        }else {
            return false;
        }

    }
}
