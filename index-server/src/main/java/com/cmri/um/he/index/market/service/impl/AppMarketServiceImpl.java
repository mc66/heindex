package com.cmri.um.he.index.market.service.impl;

import com.cmri.um.he.index.market.dao.AppMarketDao;
import com.cmri.um.he.index.market.entity.AppMarketEntity;
import com.cmri.um.he.index.market.service.AppMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
public class AppMarketServiceImpl implements AppMarketService {

    @Autowired
    private AppMarketDao appMarketDao;

    @Override
    public List<Map<String, Object>> getAppMarketList(int category,String month) {
        return appMarketDao.getAppMarketList(category,month);
    }
}
