package com.cmri.um.he.index.service.impl;

import com.cmri.um.he.index.dao.AppProblemDao;
import com.cmri.um.he.index.service.AppProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询app某一具体维度的问题列表
 * Created by machao on 2018/7/3.
 */
@Service
public class AppProblemServiceImpl implements AppProblemService {

    @Autowired
    AppProblemDao appProblemDao;

    @Override
    public List<Map<String, Object>> getAppProblem(int app, String dimensions,String month) {
        return appProblemDao.getAppProblem(app,dimensions,month);
    }
}
