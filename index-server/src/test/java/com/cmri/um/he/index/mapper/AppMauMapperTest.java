package com.cmri.um.he.index.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Map;

import java.util.ArrayList;


/**
 * @author zhuyin
 * Created on 2018/06/13 15:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppMauMapperTest {
    @Autowired
    private AppMauMapper mapper;

    private String month = "201712";
    private List items=new ArrayList<>();

    @Test
    public void count() {
        int count = mapper.count(month);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void find() {
        List<Map<String,Object>> items = mapper.find(month, 1, 1);
        Assert.assertTrue(items.size() > 0);
    }

    @Test
    public void countOfCategory() {
        int count = mapper.countOfCategory(month, "移动视频类");
        Assert.assertTrue(count > 0);
    }

    @Test
    public void findByCategory() {
        List<Map<String,Object>> items = mapper.findByCategory(month, "移动视频类", 1, 1);
        Assert.assertTrue(items.size() > 0);
    }
}