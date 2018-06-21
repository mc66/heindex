package com.cmri.um.he.index.config;

import com.cmri.spring.common.configure.ZWebConfig;
import com.cmri.spring.common.controller.ZErrorController;
import com.cmri.spring.common.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 服务的Spring配置类
 *
 * @author zhuyin
 * Created on 2018/6/4
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {ZErrorController.class, GlobalExceptionHandler.class})
public class WebConfig extends ZWebConfig {
}
