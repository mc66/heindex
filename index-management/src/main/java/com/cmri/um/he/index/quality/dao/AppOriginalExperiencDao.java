package com.cmri.um.he.index.quality.dao;

import com.cmri.um.he.index.quality.entity.AppOriginalExperienceEntity;
import com.cmri.um.he.index.quality.mapper.AppOriginalExperiencMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 查询某类未添加应用体验接口的数据库访问
 *
 * @author Limin
 * Created on 2018/6/26
 */
@Repository
public class AppOriginalExperiencDao extends BaseDao {

    @Autowired
    private AppOriginalExperiencMapper appOriginalExperiencMapper;

    public List<Map<String,Object>> findWhole() {
        return appOriginalExperiencMapper.findWhole();
    }

    public List<Map<String,Object>> find( Integer category) {
        return appOriginalExperiencMapper.findExperience( category);
    }


    public int updateExperience(Integer id, Double experience) {
        return appOriginalExperiencMapper.updateExperience(id,experience);
    }

    public int updateExperienceAll(AppOriginalExperienceEntity experie) {
        return appOriginalExperiencMapper.updateExperienceAll(experie);
    }
}
