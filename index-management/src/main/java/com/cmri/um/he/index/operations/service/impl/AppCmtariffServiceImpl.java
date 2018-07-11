package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppCmtariffDao;
import com.cmri.um.he.index.operations.service.AppCmtariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 	渠道、营销、资费、服务原始数据
 *
 * @author limin
 * Created on 2018/7/10
 */
@Service
public class AppCmtariffServiceImpl implements AppCmtariffService {

    @Autowired
    private AppCmtariffDao appCmtariffDao;
}
