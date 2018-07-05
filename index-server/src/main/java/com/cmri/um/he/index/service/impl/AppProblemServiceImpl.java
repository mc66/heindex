package com.cmri.um.he.index.service.impl;

import com.cmri.um.he.index.dao.AppProblemDao;
import com.cmri.um.he.index.service.AppProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询app某维度的问题
 * @author machao
 * Created on 2018/7/4
 */
@Service
public class AppProblemServiceImpl implements AppProblemService {

    @Autowired
    AppProblemDao appProblemDao;

    @Override
    public List<Map<String, Object>> getAppOperationProblem(int id, String dimensions, String month) {
        return appProblemDao.getAppOperationProblem(id, dimensions, month);
    }

    @Override
    public List<Map<String, Object>> getAppQualityProblem(int id, String dimensions,String month) {
        return appProblemDao.getAppQualityProblem(id,dimensions,month);
    }
}
