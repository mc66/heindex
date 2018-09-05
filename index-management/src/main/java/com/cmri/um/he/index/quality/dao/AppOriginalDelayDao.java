package com.cmri.um.he.index.quality.dao;

import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppOriginalDelayEntity;
import com.cmri.um.he.index.quality.mapper.AppOriginalDelayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 品质得分中时延、功耗
 * @author lch
 * Created on 2018/06/27 09:54
 */
@Repository
public class AppOriginalDelayDao extends BaseDao{
    @Autowired
    private AppOriginalDelayMapper mapper;

    /**
     * 根据应用类别查询app
     * @param category
     * @return
     */
    public List<Map<String, Object>> findAppByCategory(Integer category) {
        return mapper.findAppByCategory(category);
    }

    /**
     * 新增时延功耗原始数据
     * @param entity
     * @return
     */
    public int saveOriginal(AppOriginalDelayEntity entity){
        return mapper.saveOriginal(entity);
    }

    /**
     * 保存延时计算值,这里顺便把app,category,experience,status,version,month,atime补全
     * @param
     * @return
     */
    public int saveDelay(AppCalculationQualityEntity entity){
        return mapper.saveDelay(entity);
    }

    /**
     * 修改时延
     * @param entity
     * @return
     */
    public int updateDelay(AppCalculationQualityEntity entity){return mapper.updateDelay(entity);}

}
