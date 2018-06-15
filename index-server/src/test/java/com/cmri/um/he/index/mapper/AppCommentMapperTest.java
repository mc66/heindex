package com.cmri.um.he.index.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lch
 * Created on 2018/06/13 15:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppCommentMapperTest {
    @Autowired
    private AppCommentMapper commentMapper;
    private String month = "201806";
    private List items=new ArrayList<>();

    @Test
    public void countOfCategory() {
        int count = commentMapper.countOfCategory(month, 1);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void findByCategory() {
        items = commentMapper.findByCategory(month, 1, 1, 1);
        Assert.assertTrue(items.size() > 0);
    }
}
