package com.cmri.um.he.index.market.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
/**
 * 市场指数的关键指标数据
 * @author shihao/machao
 * Created on 2018/6/13
 */
public interface AppMarketService {

    List<Map<String,Object>>  getAppMarketList(int category,@RequestParam String month);
    List<Map<String ,Object>> getMarket(int category,String month1,String month2);


}
