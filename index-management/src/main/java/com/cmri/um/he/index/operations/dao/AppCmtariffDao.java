package com.cmri.um.he.index.operations.dao;

import com.cmri.um.he.index.operations.mapper.AppCmtariffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * 	渠道、营销、资费、服务原始数据
 *
 * @author limin
 * Created on 2018/7/10
 */
@Repository
public class AppCmtariffDao extends BaseDao {

    @Autowired
    private AppCmtariffMapper appCmtariffMapper;

}
