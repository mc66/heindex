package com.cmri.um.he.index.mapper;

import com.cmri.um.he.index.entity.AppOriginalDelayEntity;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author lch
 * Created on 2018/06/27 11:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppOriginalDelayMapperTest {
    @Autowired
    private AppOriginalDelayMapper mapper;

    @org.junit.Test
    public void saveOriginalDelay() {
        AppOriginalDelayEntity entity=new AppOriginalDelayEntity(0,1,1,"V2018","真好","3g",55.6,55.69,12,0,"201806",new Date());
        int save = mapper.save(entity);
        Assert.assertTrue(save > 0);
    }
}
