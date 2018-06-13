package com.cmri.um.he.index.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

<<<<<<< HEAD
import java.util.List;
import java.util.Map;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 6ca144546c4122a8d55a382d40cabd32571066d6

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
<<<<<<< HEAD
        List<Map<String, Object>> items = mapper.find(month, 1, 1);
=======
        items = mapper.find(month, 1, 1);
>>>>>>> 6ca144546c4122a8d55a382d40cabd32571066d6
        Assert.assertTrue(items.size() > 0);
    }

    @Test
    public void countOfCategory() {
        int count = mapper.countOfCategory(month, "移动视频类");
        Assert.assertTrue(count > 0);
    }

    @Test
    public void findByCategory() {
<<<<<<< HEAD
        List<Map<String, Object>> items = mapper.findByCategory(month, "移动视频类", 1, 1);
=======
        items = mapper.findByCategory(month, "移动视频类", 1, 1);
>>>>>>> 6ca144546c4122a8d55a382d40cabd32571066d6
        Assert.assertTrue(items.size() > 0);
    }
}