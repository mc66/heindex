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
 * @author lch
 * Created on 2018/07/03 11:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppSecondQualityDelayMapperTest {
    @Autowired
    private AppSecondQualityDelayMapper mapper;
    private Integer category=1;
    private String measuring="启动时延";
    private String network="3g";
    private String month="201807";

    @Test
    public void testDelay() {
        List<Map<String, Object>> list = mapper.findQualityMeasureBySome(category, measuring, network, month);
        Assert.assertTrue(list.size() > 0);
    }
}
