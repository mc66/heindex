package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.um.he.index.service.AppOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询单个应用运营数据
 *
 * @author
 * Created on 2018/7/4
 */
@RestController
@RequestMapping("/app-operations")
@CrossOrigin
public class AppOperationsController extends ZRestController {

    @Autowired
    private AppOperationsService appOperationsService;


}
