package com.cmri.um.he.index.appview.mapper;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
/**
 * @author limin
 * Created on 2018/06/13 15:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppFeaturesMapperTest {

    @Autowired
    private AppFeaturesMapper appFeaturesMapper;
    private String month = "201801";





    @Test
    public void findByApp() {
        List<Map<String, Object>> list = appFeaturesMapper.findByApp(month, 2);
        Assert.assertTrue(list.size() > 0);
    }


}
