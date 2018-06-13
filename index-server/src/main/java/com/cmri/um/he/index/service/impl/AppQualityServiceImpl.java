package com.cmri.um.he.index.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.dao.AppQualityDao;
import com.cmri.um.he.index.service.AppQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
* 查询某类应用品质指数
* @author shihao
* Created on 2018/6/13
*/
@Service
public class AppQualityServiceImpl implements AppQualityService {
    @Autowired
    private AppQualityDao appQualityDao;

    @Override
    public PagingData<Map<String, Object>> find(String month, String category, int page, int step) {
        return new PagingData<>(appQualityDao.getCount(month, category),
                page,
                step,
                appQualityDao.getQualityList(month, category, page, step)
        );
    }

}
