package com.cmri.um.he.index.market.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 市场指数表对应实体类
 * @author machao
 * Created on 2018/7/13
 */
public class AppMarketEntity implements Serializable {
    private Integer id;
    /**
     * 应用类别id
     */
    private Integer category;
    /**
     * app应用id
     */
    private Integer app;
    /**
     * 月均活跃用户数
     */
    private double mauNumber;
    /**
     * 次月留存率
     */
    private double keepRate;
    /**
     * 月人均使用时长
     */
    private double lengthTime;
    /**
     * 月人均使用流量
     */
    private double flow;
    /**
     * 市场指数
     */
    private double market;
    /**
     * 月份
     */
    private String month;
    /**
     * 记录添加时间
     */
    private Date atime;

    public AppMarketEntity(Integer id, Integer category, Integer app, double mauNumber, double keepRate, double lengthTime, double flow, double market, String month, Date atime) {
        this.id = id;
        this.category = category;
        this.app = app;
        this.mauNumber = mauNumber;
        this.keepRate = keepRate;
        this.lengthTime = lengthTime;
        this.flow = flow;
        this.market = market;
        this.month = month;
        this.atime = atime;
    }

    @Override
    public String toString() {
        return "AppMarketEntity{" +
                "id=" + id +
                ", category=" + category +
                ", app=" + app +
                ", mauNumber=" + mauNumber +
                ", keepRate=" + keepRate +
                ", lengthTime=" + lengthTime +
                ", flow=" + flow +
                ", market=" + market +
                ", month='" + month + '\'' +
                ", atime=" + atime +
                '}' ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app;
    }

    public double getMauNumber() {
        return mauNumber;
    }

    public void setMauNumber(double mauNumber) {
        this.mauNumber = mauNumber;
    }

    public double getKeepRate() {
        return keepRate;
    }

    public void setKeepRate(double keepRate) {
        this.keepRate = keepRate;
    }

    public double getLengthTime() {
        return lengthTime;
    }

    public void setLengthTime(double lengthTime) {
        this.lengthTime = lengthTime;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public double getMarket() {
        return market;
    }

    public void setMarket(double market) {
        this.market = market;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }

    public AppMarketEntity() {
    }
}


