package com.cmri.um.he.index.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 默认展示时获取查询时间通用方法
 * @author machao
 * Created on 2018/7/30
 */
public class DefaultTime {

    public static String getDefaultTimes(String findTime,int i,String month)throws Exception{

        String st1 = month.substring(Constants.MONTH_JOINT_FRONT,Constants.MONTH_JOINT_REAR);
        String st2 = month.substring(Constants.MONTH_JOINT_REAR);
        String endTime = st1 + "-" + st2;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date parse = format.parse(endTime);
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
