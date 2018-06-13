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
 * @author lch
 * Created on 2018/06/13 15:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppCategoryMapperTest {
    @Autowired
    private AppCategoryMapper mapper;

    @Test
    public void countOfCategory() {
        List<Map<String, Object>> categorys = mapper.findCategorys();
        Assert.assertTrue(categorys.size() > 0);
    }
}
