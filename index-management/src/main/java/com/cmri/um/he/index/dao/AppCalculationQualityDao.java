package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.mapper.AppCalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * author shihao
 * Created on 2018/6/27
 */
@Repository
public class AppCalculationQualityDao extends BaseDao {

    @Autowired
    private AppCalMapper mapper;

   public int update(AppCalculationQualityEntity appCalculationQualityEntity){
      return  mapper.upadte(appCalculationQualityEntity);
   }
}
