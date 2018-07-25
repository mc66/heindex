package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.AppMarketGeneralMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AppMarketGeneralDao {
    @Autowired
    private AppMarketGeneralMapper mapper;

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

}
