package com.cmri.um.he.index.appview.mapper;

import com.cmri.um.he.index.util.StandardDeviationUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yinjnujun
 * Created on 2018/06/27 11:17
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StandardDeviationTest {

    @Test
    public void standDeviationTest(){
        double[] d = {80.0,80.8,80.8,84.2,69.5,58.5,78.5};
        Double standardDeviation = StandardDeviationUtil.getStandardDeviation(d);
        double average = StandardDeviationUtil.getAverage(d);
        System.out.println("平均值="+average);
        System.out.println("标准差="+standardDeviation);
    }

}
