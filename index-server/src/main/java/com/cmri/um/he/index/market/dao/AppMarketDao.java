package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.AppMarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import java.util.List;
import java.util.Map;
/**
 * 市场指数的关键指标数据
 * @author shihao/machao
 * Created on 2018/6/13
 */
@Repository
public class AppMarketDao extends BaseDao{

    @Autowired
    private AppMarketMapper appMarketMapper;

    /**
     * 根据应用类别id以及月份查询市场指标数据
     * @param category
     * @param month
     * @return
     */
    public List<Map<String,Object>> getAppMarketList(int category,String month){
        return appMarketMapper.getAppMarketList(category,month);
    }
    /**
     * 查询市场指数的关键指标数据(默认月份时间/根据月份区间)
     * @param category
     * @param month1
     * @return
     */
    public List<Map<String, Object>> getMarket(int category, String month1,String month2) {
        List<Map<String, Object>> list= appMarketMapper.getMarket(category,month1,month2);
        return  list;
    }

}
