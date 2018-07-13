package com.cmri.um.he.index.market.dao;

import com.cmri.um.he.index.market.mapper.AppMarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppMarketDao extends BaseDao{

    @Autowired
    private AppMarketMapper appMarketMapper;
}
