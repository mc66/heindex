package com.cmri.um.he.index.quality.dao;

import com.cmri.um.he.index.quality.entity.AppQualityEntity;
import com.cmri.um.he.index.quality.mapper.StandardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 批量添加+++的数据库访问
 *
 * @author yinjunjun
 * Created on 2018/6/28
 */
@Repository
public class StandardDao extends BaseDao{

    @Autowired
    private StandardMapper mapper;

    /**
     * 批量添加数据到数据库
     * */
    public void saveAll(List<AppQualityEntity> appQualityEntities){
        mapper.saveAll(appQualityEntities);
    }
}
