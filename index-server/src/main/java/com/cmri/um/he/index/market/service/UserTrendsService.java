package com.cmri.um.he.index.market.service;

import java.util.List;
import java.util.Map;

public interface UserTrendsService {

    List<Map<String,Object>> queryMonthsDataList(int category, int app, String month);

    List<Map<String,Object>> queryPenetrationList(int app, String month1, String month2, String status);

    List<Map<String,Object>> queryUserStatisticsList(int app, String month1, String month2, String status);
}
