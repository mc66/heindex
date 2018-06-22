package com.cmri.um.he.index.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 时延功耗,测试原始数据表对应的entity
 *
 * @author shihao
 * Created on 2018/6/22
 */
public class AppOriginalDelayEntity implements Serializable {

    private int id;
    private int app;
    private int category;
    private String version;
    private String measuring;
    private String network;
    private double measure;
    private double standard;
    private double challenge;
    private int status;
    private char month;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Date atime;

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMeasuring() {
        return measuring;
    }

    public void setMeasuring(String measuring) {
        this.measuring = measuring;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    public double getStandard() {
        return standard;
    }

    public void setStandard(double standard) {
        this.standard = standard;
    }

    public double getChallenge() {
        return challenge;
    }

    public void setChallenge(double challenge) {
        this.challenge = challenge;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public char getMonth() {
        return month;
    }

    public void setMonth(char month) {
        this.month = month;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }

    @Override
    public String toString() {
        return "AppOriginalDelayEntity{" +
                "id=" + id +
                ", app=" + app +
                ", category=" + category +
                ", version='" + version + '\'' +
                ", measuring='" + measuring + '\'' +
                ", network='" + network + '\'' +
                ", measure=" + measure +
                ", standard=" + standard +
                ", challenge=" + challenge +
                ", status=" + status +
                ", month=" + month +
                ", atime=" + atime +
                '}';
    }
}
