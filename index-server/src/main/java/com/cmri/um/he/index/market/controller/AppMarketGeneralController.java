package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.um.he.index.market.service.AppMarketGeneralService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AppMarketGeneralController extends ZRestController {

    private AppMarketGeneralService appMarketGeneralService;
}
