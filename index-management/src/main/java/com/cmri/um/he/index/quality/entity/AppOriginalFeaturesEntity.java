package com.cmri.um.he.index.quality.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能界面,体验问题数据表对应的entity
 *
 * @author shihao
 * Created on 2018/6/22
 */
public class AppOriginalFeaturesEntity implements Serializable {

    private int id;
    private int app;
    private int category;
    private String version;
    private String dimensions;
    private String des;
    private int experience;
    private int frequency;
    private int score;
    private String degree;
    private int status;
    private String month;
    private Date atime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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


    @Override
    public String toString() {
        return "AppOriginalFeaturesEntity{" +
                "id=" + id +
                ", app=" + app +
                ", category=" + category +
                ", version='" + version + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", des='" + des + '\'' +
                ", experience=" + experience +
                ", frequency=" + frequency +
                ", score=" + score +
                ", degree='" + degree + '\'' +
                ", status=" + status +
                ", month=" + month +
                ", atime=" + atime +
                '}';
    }
}
