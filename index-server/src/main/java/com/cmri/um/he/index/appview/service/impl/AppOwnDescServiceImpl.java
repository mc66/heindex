package com.cmri.um.he.index.appview.service.impl;

import com.cmri.um.he.index.appview.dao.AppOwnDescDao;
import com.cmri.um.he.index.appview.service.AppOwnDescService;
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

    @Override
    public List<Map<String, Object>> findByIdAndMonth(Integer id, String month) {
        return dao.findByIdAndMonth(id, month);
    }

    @Override
    public List<Map<String, Object>> findAppInfoByIdAndMonth(Integer id, String month, Integer flag) {
        if (flag==1){
            return dao.findAppInfo1ByIdAndMonth(id,month);
        }else if (flag==2){
            return dao.findAppInfo2ByIdAndMonth(id,month);
        }
        return null;
    }
}
