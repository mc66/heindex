package com.cmri.um.he.index.appview.mapper;

import java.math.BigDecimal;

/**
 * Created by machao on 2018/8/29.
 */
public class Test {
    public static void main(String[] args) {
        double a = 50;
        double b = 60;
        System.out.println(new BigDecimal((a-b)/b*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
    }
}
