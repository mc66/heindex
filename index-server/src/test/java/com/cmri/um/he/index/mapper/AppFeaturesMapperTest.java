package com.cmri.um.he.index.mapper;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppFeaturesMapperTest {

    @Autowired
    private AppFeaturesMapper appFeaturesMapper;
    private String month = "201801";



    @Test
    public void countOfApp() {
        int count = appFeaturesMapper.countOfApp(month, 2);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void findByApp() {
        List<Map<String, Object>> list = appFeaturesMapper.findByApp(month, 2, 0, 1);
        Assert.assertTrue(list.size() > 0);
    }


}
