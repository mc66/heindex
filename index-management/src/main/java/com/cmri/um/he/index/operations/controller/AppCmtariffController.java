package com.cmri.um.he.index.operations.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.um.he.index.operations.service.AppCmtariffService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 	渠道、营销、资费、服务原始数据
 *
 * @author limin
 * Created on 2018/7/10
 */
public class AppCmtariffController extends ZRestController{

    @Autowired
    private AppCmtariffService appCmtariffService;

}
