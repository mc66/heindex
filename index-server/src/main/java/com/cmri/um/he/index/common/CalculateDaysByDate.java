package com.cmri.um.he.index.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 	口碑指数原始数据(评论)
 * @author machao
 * Created on 2018/8/10
 */
public class CalculateDaysByDate {

    /**
     * 给定一个时间段，计算天数
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static int calculateDaysByDate(String startTime, String endTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer start = new StringBuffer(startTime);
        StringBuffer end = new StringBuffer(endTime);
        String str1 = start.insert(4, "-").insert(7,"-").toString();
        String str2 = end.insert(4, "-").insert(7,"-").toString();
        Date parse1 = format.parse(str1);
        Date parse2 = format.parse(str2);
        System.out.println(parse1.getTime()+"*************============"+parse2.getTime());
        long l = (parse2.getTime()-parse1.getTime()) / (24 * 60 * 60 * 1000)+1;
        return Integer.parseInt(String.valueOf(l));
    }

    /**
     * 给定一个时间段，计算多少个15天
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static int calculateCountByDate(String startTime, String endTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer start = new StringBuffer(startTime);
        StringBuffer end = new StringBuffer(endTime);
        String str1 = start.insert(4, "-").insert(7,"-").toString();
        String str2 = end.insert(4, "-").insert(7,"-").toString();
        Date parse1 = format.parse(str1);
        Date parse2 = format.parse(str2);
        long l = (parse2.getTime() - parse1.getTime()) / (24 * 60 * 60 * 1000*15);
        return Integer.parseInt(String.valueOf(l));
    }

    /**
     * 计算查询的时间
     * @param findTime
     * @param i
     * @param month
     * @return
     * @throws Exception
     */
    public static String getDate(String findTime,int i,String month)throws Exception{

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer mon = new StringBuffer(month);
        String str = mon.insert(4, "-").insert(7,"-").toString();
        Date parse = format.parse(str);
        cal.setTime(parse);
        if (Constants.DAY.equalsIgnoreCase(findTime)) {
            cal.add(Calendar.DATE,i);
        }
        String date=format.format(cal.getTime());
        String[] split = date.split("-");
        date=split[0]+split[1]+split[2];
        return date;
    }
}
