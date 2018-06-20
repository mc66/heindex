package com.cmri.um.he.index.service.impl;

import com.cmri.um.he.index.dao.AppEmotionDao;
import com.cmri.um.he.index.service.AppEmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 某类应用情感得分查询的服务类实现
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@Service
public class AppEmotionServiceImpl implements AppEmotionService {

    @Autowired
    private AppEmotionDao appEmotionDao;

    @Override
    public List<Map<String, Object>> find(Integer category) {
        return appEmotionDao.findByCategory(category);
    }
}
