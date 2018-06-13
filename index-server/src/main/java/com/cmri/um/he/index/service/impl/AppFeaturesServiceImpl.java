package com.cmri.um.he.index.service.impl;


import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.dao.AppFeaturesDao;
import com.cmri.um.he.index.service.AppFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param app      应用名
     * @param page     所要查询的分页，从1开始
     * @param step     每页的记录容量
     * @return
     */
    @Override
    public PagingData<Map<String, Object>> find(String month, int app, int page, int step) {
        return new PagingData<>(appFeaturesDao.countOfApp(month, app),
                page,
                step,
                appFeaturesDao.findByApp(month, app , page, step)
        );
    }


}