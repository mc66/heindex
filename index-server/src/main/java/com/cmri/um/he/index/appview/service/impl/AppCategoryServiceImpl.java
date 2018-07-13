package com.cmri.um.he.index.appview.service.impl;

import com.cmri.um.he.index.appview.dao.AppCategoryDao;
import com.cmri.um.he.index.appview.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 应用类别查询的服务类实现
 * Created on 2018/6/12.
 * @author lch
 */
@Service
public class AppCategoryServiceImpl implements AppCategoryService {
    @Autowired
    private AppCategoryDao appCategoryDao;


    @Override
    public List<Map<String, Object>> find() {
        return appCategoryDao.find();
    }

    @Override
    public List<Map<String, Object>> finds() {
        return appCategoryDao.finds();
    }
}
