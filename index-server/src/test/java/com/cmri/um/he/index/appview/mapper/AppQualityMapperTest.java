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
 * @author shihao
 * Created on 2018/06/13 15:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppQualityMapperTest {
    @Autowired
    private AppQualityMapper qumapper;

    private String month = "201806";
    @Test
    public void getCount() {
        int count = qumapper.getCount(month, "移动视频类");
        Assert.assertTrue(count > 0);
    }
    @Test
    public void getQualityList() {
        List<Map<String,Object>> items = qumapper.getQualityList(month, "移动视频类", 1, 1);
        Assert.assertTrue(items.size() > 0);
    }
}
