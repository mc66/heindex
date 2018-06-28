package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.entity.AppWeightQualityEntity;
import com.cmri.um.he.index.mapper.AppWeightQualityMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 品质得分权重
 * @author lch
 * Created on 2018/06/28 09:26
 */
public class AppWeightQualityDao {
    @Autowired
    private AppWeightQualityMapper mapper;

    public List<AppWeightQualityEntity> findQualityConfig(){
        return mapper.findQualityConfig();
    }
}
