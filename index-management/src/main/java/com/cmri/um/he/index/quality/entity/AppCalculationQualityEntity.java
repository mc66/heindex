package com.cmri.um.he.index.quality.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能界面,标准值数据表对应的entity
 *
 * @author yinjunjun
 * Created on 2018/6/25
 */
public class AppCalculationQualityEntity implements Serializable{
    private Integer id;
    private Integer app;
    private Integer category;
    private Double features;
    private Double view;
    private Double delay;
    private Double consume;
    private Double experience;
    private String version;
    private Integer status;
    private String month;
    private Date atime;

    @Override
    public String toString() {
        return "AppCalculationQualityEntity{" +
                "id=" + id +
                ", app=" + app +
                ", category=" + category +
                ", features=" + features +
                ", view=" + view +
                ", delay=" + delay +
                ", consume=" + consume +
                ", experience=" + experience +
                ", version='" + version + '\'' +
                ", status=" + status +
                ", month='" + month + '\'' +
                ", atime=" + atime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getFeatures() {
        return features;
    }

    public void setFeatures(Double features) {
        this.features = features;
    }

    public Double getView() {
        return view;
    }

    public void setView(Double view) {
        this.view = view;
    }

    public Double getDelay() {
        return delay;
    }

    public void setDelay(Double delay) {
        this.delay = delay;
    }

    public Double getConsume() {
        return consume;
    }

    public void setConsume(Double consume) {
        this.consume = consume;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public AppCalculationQualityEntity() {

    }

    public AppCalculationQualityEntity(Integer id, Integer app, Integer category, Double features, Double view, Double delay, Double consume, Double experience, String version, Integer status, String month, Date atime) {

        this.id = id;
        this.app = app;
        this.category = category;
        this.features = features;
        this.view = view;
        this.delay = delay;
        this.consume = consume;
        this.experience = experience;
        this.version = version;
        this.status = status;
        this.month = month;
        this.atime = atime;
    }

}
