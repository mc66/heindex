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
     * 查询人均时长和人均流量(根据月份区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String, Object>> getLengthTimeBymonth(Integer app, String month1, String month2 ){
        return mapper.getLengthTimeBymonth(app,month1,month2);
    }
    /**
     * 查询人均时长和人均流量(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String, Object>> getLengthTimeByWeek(Integer app, String month1, String month2 ){
        return mapper.getLengthTimeByWeek(app,month1,month2);
    }
    /**
     * 查询人均时长和人均流量(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String, Object>> getLengthTimeByDate(Integer app, String month1, String month2 ){
        return mapper.getLengthTimeBydate(app,month1,month2);
    }

    /**
     * 查询应用概括统计(根据月份区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String,Object>> quaryGeneralStatisticByMonth(Integer app,String month1,String month2 ){
        return mapper.quaryGeneralStatisticByMonth(app,month1,month2);
    }
    /**
     * 查询应用概括统计(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String,Object>> quaryGeneralStatisticByWeek(Integer app,String month1,String month2 ){
        return mapper.quaryGeneralStatisticByWeek(app,month1,month2);
    }
    /**
     * 查询应用概括统计(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String,Object>> quaryGeneralStatisticByDate(Integer app,String month1,String month2){
        return mapper.quaryGeneralStatisticByDate(app,month1,month2);
    }

    /**
     * 查询次月存留率(根据月份区间)
     * @return
     */
    public List<Map<String, Object>> getRateByMonth(Integer app, String month1, String month2){
        return mapper.getRateByMonth(app,month1,month2);
    }

    /**
     * 查询该app的最新月份数据
     * @param app
     * @return
     */
    public String getNewMonth(Integer app){
            String  month=  mapper.getNewMonth(app);
            return  month;
    }
    /**
     * 查询次月存留率(根据周区间)
     * @return
     */
    public List<Map<String, Object>> getRateByWeek(Integer app, String month1, String month2){
        return mapper.getRateByWeek(app,month1,month2);
    }

    /**
     * 查询该app的最新周份数据
     * @param app
     * @return
     */
    public String getNewWeek(Integer app){
        String  month=  mapper.getNewWeek(app);
        return  month;
    }
    /**
     * 查询次月存留率(根据日区间)
     * @return
     */
    public List<Map<String, Object>> getRateByDate(Integer app, String month1, String month2){
        return mapper.getRateByDate(app,month1,month2);
    }
    /**
     * 查询该app的最新周份数据
     * @param app
     * @return
     */
    public String getNewDate(Integer app){
        String  month=  mapper.getNewDate(app);
        return  month;
    }
    /**
     * 查询月活跃用户(根据月份区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String, Object>> getUserNumberByMonth(Integer app, String month1, String month2 ) {
        return mapper.getUserNumberByMonth(app,month1,month2);
    }
    /**
     * 查询月活跃用户(根据周区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String, Object>> getUserNumberByWeek(Integer app, String month1, String month2 ) {
        return mapper.getUserNumberByWeek(app,month1,month2);
    }
    /**
     * 查询月活跃用户(根据日区间)
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String, Object>> getUserNumberBydate(Integer app, String month1, String month2 ) {
        return mapper.getUserNumberBydate(app,month1,month2);
    }
    /**
     *  查询当月数据
     * @param category
     * @param app
     * @param month
     * @return
     */
    public List<Map<String,Object>> getMarketMonth(int category, int app, String month) {
        return  mapper.getMarketMonth(category,app,month);
    }

    /**
     *  查询累计用户数
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String,Object>> getCumulativeListByMonth(int app, String month1, String month2) {
        return  mapper.getCumulativeListByMonth(app,month1,month2);
    }
    /**
     *  查询累计用户数
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String,Object>> getCumulativeListByWeek(int app, String month1, String month2) {
        return  mapper.getCumulativeListByWeek(app,month1,month2);
    }
    /**
     *  查询累计用户数
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    public List<Map<String,Object>> getCumulativeListByDate(int app, String month1, String month2) {
        return  mapper.getCumulativeListByDate(app,month1,month2);
    }
}
