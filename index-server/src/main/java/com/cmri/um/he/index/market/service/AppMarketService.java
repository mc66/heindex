package com.cmri.um.he.index.market.service;

import com.cmri.um.he.index.market.entity.AppMarketEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import java.util.List;
import java.util.Map;

public interface AppMarketService {

    /**
     * 根据应用类别id以及月份查询市场指标数据
     * @param category
     * @param month
     * @return
     */
    List<Map<String,Object>> getAppMarketList(int category,@RequestParam String month);
    List<Map<String ,Object>> getMarket(int category,String month);

}
