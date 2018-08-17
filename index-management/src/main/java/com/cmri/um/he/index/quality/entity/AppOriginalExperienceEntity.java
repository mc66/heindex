package com.cmri.um.he.index.quality.entity;

import java.io.Serializable;

/**
 * 体验数据表对应的entity
 *
 * @author limin
 * Created on 2018/6/25
 */
public class AppOriginalExperienceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String month;

    private Integer category;

    private Integer app;

    private String version;

    private Double experience;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }
}
