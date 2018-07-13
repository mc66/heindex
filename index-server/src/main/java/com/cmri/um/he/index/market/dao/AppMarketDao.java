package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.AppMarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AppMarketDao extends BaseDao{

    @Autowired
    private AppMarketMapper appMarketMapper;

    public List<Map<String, Object>> getMarket(int category, String month,String month2) {
        List<Map<String, Object>> list= appMarketMapper.getMarket(category,month,month2);
        return  list;
    }
}
