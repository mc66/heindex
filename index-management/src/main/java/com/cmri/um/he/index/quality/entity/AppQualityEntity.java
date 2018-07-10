package com.cmri.um.he.index.quality.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 创建表 app_quality 实体对象
 * @author yinjunjun
 * Created on 2018/6/28
 * */
public class AppQualityEntity implements Serializable {
    private Integer id;
    private Integer app;
    private Integer category;
    private Double features;
    private Double views;
    private Double delay;
    private Double consume;
    private Double experience;
    private Double qindex;
    private String version;
    private String month;
    private Date atime;

    @Override
    public String toString() {
        return "AppQuality{" +
                "id=" + id +
                ", app=" + app +
                ", category=" + category +
                ", features=" + features +
                ", views=" + views +
                ", delay=" + delay +
                ", consume=" + consume +
                ", experience=" + experience +
                ", qindex=" + qindex +
                ", version='" + version + '\'' +
                ", month=" + month +
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

    public Integer getCatrgory() {
        return category;
    }

    public void setCatrgory(Integer category) {
        this.category = category;
    }

    public Double getFeatures() {
        return features;
    }

    public void setFeatures(Double features) {
        this.features = features;
    }

    public Double getViews() {
        return views;
    }

    public void setViews(Double views) {
        this.views = views;
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

    public Double getQindex() {
        return qindex;
    }

    public void setQindex(Double qindex) {
        this.qindex = qindex;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public AppQualityEntity() {

    }

    public AppQualityEntity(Integer id, Integer app, Integer category, Double features, Double views, Double delay, Double consume, Double experience, Double qindex, String version, String month, Date atime) {

        this.id = id;
        this.app = app;
        this.category = category;
        this.features = features;
        this.views = views;
        this.delay = delay;
        this.consume = consume;
        this.experience = experience;
        this.qindex = qindex;
        this.version = version;
        this.month = month;
        this.atime = atime;
    }
}
