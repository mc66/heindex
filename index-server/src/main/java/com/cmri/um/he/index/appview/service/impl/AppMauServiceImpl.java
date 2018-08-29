package com.cmri.um.he.index.appview.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.appview.dao.AppMauDao;
import com.cmri.um.he.index.appview.service.AppActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 应用月活查询的服务类实现
 *
 * @author zhuyin
 * Created on 2018/6/4
 */
@Service
public class AppMauServiceImpl implements AppActiveService {
    @Autowired
    private AppMauDao activeDao;

    @Override
    public PagingData<Map<String, Object>> find(String month, int page, int step) {
        PagingData<Map<String, Object>> pagingData = new PagingData<>(activeDao.count(month),
                page,
                step,
                activeDao.find(month, page, step)
        );
        return getPagingData(pagingData);
    }

    @Override
    public PagingData<Map<String, Object>> find(String month, String category, int page, int step) {
        PagingData<Map<String, Object>> pagingData = new PagingData<>(activeDao.countOfCategory(month, category),
                page,
                step,
                activeDao.findByCategory(month, category, page, step)
        );
        return getPagingData(pagingData);

    }
    public static PagingData<Map<String, Object>> getPagingData(PagingData<Map<String, Object>> pagingData){
        List<Map<String, Object>> data = pagingData.getData();
        for (Map<String, Object> datum : data) {
            double datum1 = (double)datum.get("datum");
            if (datum1>0){
                datum.put("status",1);
            }else {
                datum.put("status",0);
            }
        }
        return pagingData;
    }

}
