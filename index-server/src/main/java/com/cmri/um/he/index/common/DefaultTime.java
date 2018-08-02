package com.cmri.um.he.index.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 默认展示时获取查询时间通用方法
 * @author machao
 * Created on 2018/7/30
 */
public class DefaultTime {


//    public static void main(String[] args) throws Exception {
//
//        String times = getDefaultTimes(Constants.MONTH, 6, "2018-09");
//
//        System.out.println(times);
//        Map<String, String> defaultTime = getDefaultTime(Constants.MONTH, 6);
//
//        System.out.println(defaultTime.get("startTime"));
//    }

    public static Map<String,String> getDefaultTime(String findTime,int i){
        HashMap<String, String> map = new HashMap<>(2);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
       // String endTime=format.format(new Date());
        if (Constants.WEEK.equalsIgnoreCase(findTime)) {
            cal.add(Calendar.WEEK_OF_MONTH, -i);
        }
        else if(Constants.MONTH.equalsIgnoreCase(findTime)){
            cal.add(Calendar.MONTH, -i);
        }
        else if (Constants.YEAR.equalsIgnoreCase(findTime)) {
            cal.add(Calendar.YEAR, -i);
        }
        String startTime=format.format(cal.getTime());

        String[] split = startTime.split("-");
        startTime=split[0]+split[1];
//        String[] split1 = endTime.split("-");
//        endTime=split1[0]+split1[1];
        map.put("startTime",startTime);
       // map.put("endTime",endTime);
        return map;

    }
    public static String getDefaultTimes(String findTime,int i,String month)throws Exception{

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date parse = format.parse(month);
        cal.setTime(parse);
        if (Constants.WEEK.equalsIgnoreCase(findTime)) {
            cal.add(Calendar.WEEK_OF_MONTH, -i);
        }
        else if(Constants.MONTH.equalsIgnoreCase(findTime)){
            cal.add(Calendar.MONTH, -i);
        }
        else if (Constants.YEAR.equalsIgnoreCase(findTime)) {
            cal.add(Calendar.YEAR, -i);
        }
        String startTime=format.format(cal.getTime());
        String[] split = startTime.split("-");
        startTime=split[0]+split[1];

        return startTime;

    }
}
