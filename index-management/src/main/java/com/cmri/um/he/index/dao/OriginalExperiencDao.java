package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.OriginalExperiencMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OriginalExperiencDao extends BaseDao {

    @Autowired
    private OriginalExperiencMapper originalExperiencMapper;

    public List<Map<String,Object>> find( Integer category) {
        return originalExperiencMapper.findExperience( category);
    }
}
