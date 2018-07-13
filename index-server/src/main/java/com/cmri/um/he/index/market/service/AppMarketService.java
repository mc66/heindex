package com.cmri.um.he.index.market.service;

import java.util.List;
import java.util.Map;

public interface AppMarketService {

    List<Map<String ,Object>> getMarket(int category,String month);

}
