package com.cmri.um.he.index.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
/**
 * @author yijunjun
 * Created on 2018/06/13 15:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppEmotionMapperTest {
    @Autowired
    private AppEmotionMapper mapper;
    private String month = "201707";

    @Test
    public void findByCategory(){
        List<Map<String, Object>> items = mapper.findByCategory(1);
        Assert.assertTrue(items.size()>0);
    }

}
