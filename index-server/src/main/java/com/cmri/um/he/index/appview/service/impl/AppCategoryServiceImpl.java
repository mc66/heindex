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
    public List<Map<String, Object>> getApps() {
        List<Map<String, Object>> list = appCategoryDao.getCategory();
        for (Map<String, Object> map : list) {
            List<Map<String, Object>> lists =appCategoryDao.getCategoryByEmotion((int)map.get("id"));
            if(lists!=null){
                map.put("children",lists);
            }

        }
        for (int i=0;i<list.size();i++) {
            List<Map<String, Object>>  children = (List<Map<String,Object>>) list.get(i).get("children");
            if(children.size()==0){
               list.remove(list.get(i));
               i--;
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> find() {
        return appCategoryDao.find();
    }

    @Override
    public List<Map<String, Object>> finds() {
        return appCategoryDao.finds();
    }
    @Override
    public List<Map<String, Object>> findAppByCategory(Integer category) {
        return appCategoryDao.findAppByCategory(category);
    }
}
