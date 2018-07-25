package com.cmri.um.he.index.market.service;

import java.util.List;
import java.util.Map;

public interface AppMarketGeneralService {

    /**
     * 查询人均时长和人均流量
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    List<Map<String,Object>> getLengthTime(Integer app,String month1,String month2);

    /**
     * 查询应用概括统计
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    List<Map<String,Object>> quaryGeneralStatistic(Integer app,String month1,String month2);


    List<Map<String, Object>> test();
    List<Map<String, Object>> getUserNumber(Integer app,String month1,String month2);
    /**
     *  查询当月数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    List<Map<String,Object>> getAppMarketList(int category, int app, String month);



    /**
     *  查询累计用户数 month2
     * @param app
     * @param month2
     * @return
     */
    List<Map<String,Object>> getCumulative(int app, String month2);

    /**
     *  查询累计用户数
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    List<Map<String,Object>> getCumulativeList(int app, String month1, String month2);
}
