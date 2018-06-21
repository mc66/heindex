package com.cmri.um.he.index.service.impl;


import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.dao.AppFeaturesDao;
import com.cmri.um.he.index.service.AppFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 应用的关键点查询的服务类
 *
 * @author Limin
 * Created on 2018/6/13
 */
@Service
public class AppFeaturesServiceImpl implements AppFeaturesService {

    @Autowired
    private AppFeaturesDao appFeaturesDao;

    /**
     *    查询指定月份、应用名的应用的关键点数据
     * @param month    月份，格式示例'201712'
     * @param category      应用名
     * @return
     */
    @Override
    public List<Map<String, Object>> find(String month,int category) {
        return appFeaturesDao.findByApp(month,category);
    }
}