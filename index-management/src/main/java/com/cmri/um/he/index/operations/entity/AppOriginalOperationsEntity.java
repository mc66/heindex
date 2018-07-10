package com.cmri.um.he.index.operations.entity;

import java.util.Date;

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
    private int dimensionsId;
    //记录添加时间
    private Date atime;


    public AppOriginalOperationsEntity(int id, String month, int category, int app, String version, String measureIndex, String specificChannel, String serverFrom, int measureValue, String explain, int dimensionsId, Date atime) {
        this.id = id;
        this.month = month;
        this.category = category;
        this.app = app;
        this.version = version;
        this.measureIndex = measureIndex;
        this.specificChannel = specificChannel;
        this.serverFrom = serverFrom;
        this.measureValue = measureValue;
        this.explain = explain;
        this.dimensionsId = dimensionsId;
        this.atime = atime;
    }

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

    public int getDimensionsId() {
        return dimensionsId;
    }

    public void setDimensionsId(int dimensionsId) {
        this.dimensionsId = dimensionsId;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }

    @Override
    public String toString() {
        return "AppOriginalOperationsEntity{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", category=" + category +
                ", app=" + app +
                ", version='" + version + '\'' +
                ", measureIndex='" + measureIndex + '\'' +
                ", specificChannel='" + specificChannel + '\'' +
                ", serverFrom='" + serverFrom + '\'' +
                ", measureValue=" + measureValue +
                ", explain='" + explain + '\'' +
                ", dimensionsId=" + dimensionsId +
                ", atime=" + atime +
                '}';
    }
}
