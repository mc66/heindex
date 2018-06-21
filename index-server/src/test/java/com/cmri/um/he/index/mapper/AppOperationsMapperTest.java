package com.cmri.um.he.index.mapper;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.service.AppOperationsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
/**
 * @author machao
 * Created on 2018/06/13 15:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppOperationsMapperTest {
    @Autowired
    private AppOperationsMapper appOperationsMapper;

    @Autowired
    private AppOperationsService appOperationsService;
    private String month = "201706";

    @Test
    public void count() {
        int count = appOperationsMapper.countOfCategory(month,"移动视频类");
        Assert.assertTrue(count > 0);
    }

    @Test
    public void find() {
        PagingData<Map<String, Object>> items = appOperationsService.getOperationsList(month, "移动视频类", 1, 1);
        //List<Map<String, Object>> items=appOperationsMapper.getOperationsList(month,"移动视频类",1,1);
        Assert.assertTrue(items.size() > 0);
    }

}