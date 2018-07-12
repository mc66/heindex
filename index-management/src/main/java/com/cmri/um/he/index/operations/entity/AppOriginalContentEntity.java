package com.cmri.um.he.index.operations.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 运营指数内容原始数据表
 * @author shihao
 * Created on 2018/7/11
 */
public class AppOriginalContentEntity implements Serializable {

    private int id;
    //测评时间
    private String month;
    //应用类别id
    private int category;
    //应用名称id
    private int app;
    //产品版本
    private String version;
    //抽样内容名称
    private String contentName;
    //抽样内容归属
    private String contentHome;
    //测量指标
    private String measuredIndex;
    //测量问题说明
    private String measuredProblem;
    //测量值（1/0）
    private int measuredValue;
    //内容分类id
    private  int contentId;
    //备注
    private String remarks;
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

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentHome() {
        return contentHome;
    }

    public void setContentHome(String contentHome) {
        this.contentHome = contentHome;
    }

    public String getMeasuredIndex() {
        return measuredIndex;
    }

    public void setMeasuredIndex(String measuredIndex) {
        this.measuredIndex = measuredIndex;
    }

    public String getMeasuredProblem() {
        return measuredProblem;
    }

    public void setMeasuredProblem(String measuredProblem) {
        this.measuredProblem = measuredProblem;
    }

    public int getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(int measuredValue) {
        this.measuredValue = measuredValue;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }
}
