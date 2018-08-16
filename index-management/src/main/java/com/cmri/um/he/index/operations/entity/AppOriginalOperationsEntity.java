package com.cmri.um.he.index.operations.entity;

import java.util.Date;
/**
 * 运营指数个维度原始数据表
 * @author shihao
 * Created on 2018/7/11
 */
public class AppOriginalOperationsEntity {

    private int id;
    //测评时间
    private String  month;
    //'产品类型id'
    private int category;
    //'app应用id'
    private int app;
    //产品版本
    private String version;
    //测量指标
    private String measureIndex;
    //具体渠道（仅渠道)
    private String specificChannel;
    //具体服务形式（仅服务)
    private String serverFrom;
    //测量值（1/0）
    private int measureValue;
    //详细说明
    private String explain;
    //产品维度ID
    private String dimensions;
    //记录添加时间
    private Date atime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMeasureIndex() {
        return measureIndex;
    }

    public void setMeasureIndex(String measureIndex) {
        this.measureIndex = measureIndex;
    }

    public String getSpecificChannel() {
        return specificChannel;
    }

    public void setSpecificChannel(String specificChannel) {
        this.specificChannel = specificChannel;
    }

    public String getServerFrom() {
        return serverFrom;
    }

    public void setServerFrom(String serverFrom) {
        this.serverFrom = serverFrom;
    }

    public int getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(int measureValue) {
        this.measureValue = measureValue;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }
}
