package com.cmri.um.he.index.service.impl;

import com.cmri.um.he.index.dao.AppOwnDescDao;
import com.cmri.um.he.index.service.AppOwnDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 咪咕视频介绍以及所属类品质分数查询的服务类实现
 *
 * @author yinjunjun
 * Created on 2018/6/13
 */
@Service
public class AppOwnDescServiceImpl implements AppOwnDescService {

    @Autowired
    private AppOwnDescDao dao;
    @Override
    public List<Map<String, Object>> findByAppId(Integer id, String month) {
        return dao.findByAppId(id, month);
    }
}
