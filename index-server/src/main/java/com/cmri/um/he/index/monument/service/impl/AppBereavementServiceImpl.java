package com.cmri.um.he.index.monument.service.impl;

import com.cmri.um.he.index.monument.dao.AppBereavementDao;
import com.cmri.um.he.index.monument.service.AppBereavementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 情感指数变化趋势的服务类的服务类实现
 *
 * @author yinjunjun
 * Created on 2018/6/10
 */
@Service
public class AppBereavementServiceImpl implements AppBereavementService {

    @Autowired
    private AppBereavementDao dao;

    @Override
    public List<Map<String, Object>> findBereavement(Integer category, String startTime, String endTime) {
        List<Map<String, Object>> bereavment = dao.findBereavement(category, startTime, endTime);
        List<String> category1 = dao.findCategory(category);
        Map<String, Object> map = new HashMap<>(16);
        map.put("app",category1);
        bereavment.add(map);

        return bereavment;
    }

    @Override
    public List<Map<String, Object>> findMoonEmotion(Integer category, String startTime) {
        List<Map<String, Object>> bereavment = dao.findMoonEmotion(category, startTime);
        return bereavment;

    }
}
