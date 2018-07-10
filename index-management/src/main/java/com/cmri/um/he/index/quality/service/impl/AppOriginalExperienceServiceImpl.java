package com.cmri.um.he.index.quality.service.impl;

import com.cmri.um.he.index.quality.dao.AppOriginalExperiencDao;
import com.cmri.um.he.index.quality.service.AppOriginalExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 查询某类未添加应用体验接口的服务类的服务类实现
 * @author limin
 * Created on 2018/6/26
 */
@Service
public class AppOriginalExperienceServiceImpl implements AppOriginalExperienceService {

    @Autowired
    private AppOriginalExperiencDao appOriginalExperiencDao;

    @Override
    public List<Map<String, Object>> findWhole() {
        return appOriginalExperiencDao.findWhole();
    }

    @Override
    public List<Map<String, Object>> find(  Integer category) {

        return appOriginalExperiencDao.find( category);
    }

    @Override
    public int updateExperience(Integer id, Double experience) {
        return appOriginalExperiencDao.updateExperience(id,experience);
    }
}
