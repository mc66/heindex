package com.cmri.um.he.index.market.service;

import java.util.List;
import java.util.Map;

public interface AppMarketGeneralService {
    List<Map<String, Object>> test();
    List<Map<String, Object>> getUserNumber(Integer app,String month1,String month2);
}
