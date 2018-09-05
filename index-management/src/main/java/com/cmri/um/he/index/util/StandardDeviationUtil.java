package com.cmri.um.he.index.util;

import java.text.DecimalFormat;

/**
 * 求取每一个维度的平均值和方差
 * @author yjj
 * Created on 2018/06/22
 */
public class StandardDeviationUtil {

    /**
     * 格式化Duble  取小数点后四位
     */
    private final static DecimalFormat DF=new DecimalFormat("######.00");
    /**
     * 求取平均值
     * @param x 维度数组
     * @return 这个维度的平均值
     */
    public static double getAverage(Double[] x){
        int m = x.length;
        double sum = 0;
        for(int i = 0;i < m;i++){
            sum += x[i];
        }
        double d  = sum / m;
        String format = DF.format(d);
        Double aDouble = Double.valueOf(format);
        return aDouble;
    }

    /**
     * 求取标准差σ=sqrt(s^2)
     * @param x 维度数组
     * @return 这个维度的标准差
     */
    public static Double getStandardDeviation(Double[] x) {
        int m=x.length;
        double sum=0;
        for(int i=0;i<m;i++){
            sum+=x[i];
        }
        double dAve=sum/m;
        double dVar=0;
        for(int i=0;i<m;i++){
            dVar+=(x[i]-dAve)*(x[i]-dAve);
        }
        double d= Math.sqrt(dVar/m);
        String format = DF.format(d);
        Double aDouble = Double.valueOf(format);
        return aDouble;
    }

    /**
     * 求取评分和
     * @param x 评分
     * @return 这个维度的和
     */
    public static int getSum(int[] x){
        int m = x.length;
        int sum = 0;
        for(int i = 0;i < m;i++){
            sum += x[i];
        }
        return sum;
    }

}
