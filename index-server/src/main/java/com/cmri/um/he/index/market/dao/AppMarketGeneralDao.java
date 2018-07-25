package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.AppMarketGeneralMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AppMarketGeneralDao extends BaseDao{

    @Autowired
    private AppMarketGeneralMapper mapper;

    /**
     * 查询人均时长和人均流量
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String, Object>> getLengthTime(Integer app, String month1, String month2){
        return mapper.getLengthTime(app,month1,month2);
    }

    /**
     * 查询应用概括统计
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String,Object>> quaryGeneralStatistic(Integer app,String month1,String month2){
        return mapper.quaryGeneralStatistic(app,month1,month2);
    }

    /**
     * 查询次月存留率
     * @return
     */
    public List<Map<String, Object>> test(){
        return mapper.test();
    }

    /**
     * 查询月活跃用户
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String, Object>> getUserNumber(Integer app, String month1, String month2) {
        return mapper.getUserNumber(app,month1,month2);
    }

    public List<Map<String,Object>> getMarketMonth(int category, int app, String month) {
        return  mapper.getMarketMonth(category,app,month);
    }

    public List<Map<String,Object>> getMarketMonth1(int category, int app, String month2) {
        return  mapper.getMarketMonth1(category,app,month2);
    }

    public List<Map<String,Object>> getCumulative(int app, String month1, String month2) {
        return  mapper.getCumulative(app,month1,month2);
    }

    public List<Map<String,Object>> getCumulativeList(int app, String month1, String month2) {
        return  mapper.getCumulative(app,month1,month2);
    }
}
