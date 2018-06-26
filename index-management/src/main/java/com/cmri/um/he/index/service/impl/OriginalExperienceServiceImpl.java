package com.cmri.um.he.index.service.impl;

import com.cmri.um.he.index.dao.OriginalExperiencDao;
import com.cmri.um.he.index.service.OriginalExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OriginalExperienceServiceImpl implements OriginalExperienceService {

    @Autowired
    private OriginalExperiencDao originalExperiencDao;

    @Override
    public List<Map<String, Object>> find(  Integer category) {

        return originalExperiencDao.find( category);
    }
}
