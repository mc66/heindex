package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.entity.AppMarketEntity;
import com.cmri.um.he.index.market.mapper.AppMarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
}
