package com.cmri.um.he.index.quality.dao;

import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.mapper.AppOriginalCalculationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * author shihao
 * Created on 2018/6/27
 */
@Repository
public class AppCalculationQualityDao extends BaseDao {

    @Autowired
    private AppOriginalCalculationMapper mapper;

   public int update(AppCalculationQualityEntity appCalculationQualityEntity){
      return  mapper.upadte(appCalculationQualityEntity);
   }
}
