package com.cmri.um.he.index;

import cmri.utils.configuration.ConfigManager;
import cmri.utils.web.NetworkHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动类
 *
 * @author zhuyin
 * Created on 2018/6/4
 */
@SpringBootApplication
public class HeIndexApp {
    static {
        ConfigManager.enableAutoReload(true);
        NetworkHelper.setDefaultProxy();
    }

    public static void main(String[] args) {
        SpringApplication.run(HeIndexApp.class, args);
    }
}
