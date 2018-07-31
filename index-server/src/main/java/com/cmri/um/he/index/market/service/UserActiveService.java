package com.cmri.um.he.index.market.service;

import java.util.List;
import java.util.Map;

public interface UserActiveService {

    List<Map<String,Object>> getActiveMonthData(int category, int app, String month);

    List<Map<String,Object>> queryActivityList(String month1, int app, String month2, String state);

    List<Map<String,Object>> queryNumberDayList(String month1, int app, String month2, String state);

    List<Map<String,Object>> queryBehaviorList(String month1, int app, String month2, String state);

    List<Map<String,Object>> queryStatisticalList(String month1, int app, String month2, String state);
}
