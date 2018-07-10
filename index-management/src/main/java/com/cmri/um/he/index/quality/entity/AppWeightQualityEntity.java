package com.cmri.um.he.index.quality.entity;

import java.io.Serializable;

/**
 * 品质得分权重实体类
 * @author lch
 * Created on 2018/06/27 17:21
 */
public class AppWeightQualityEntity implements Serializable{
    private int id;
    private int category;
    private double w3g;
    private double w4g;
    private double wwlan;
    private int whigh;
    private int wmiddle;
    private int wlow;
    private double wfeatures;
    private double wview;
    private double wdelay;
    private double wconsume;
    private double wexperience;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getW3g() {
        return w3g;
    }

    public void setW3g(double w3g) {
        this.w3g = w3g;
    }

    public double getW4g() {
        return w4g;
    }

    public void setW4g(double w4g) {
        this.w4g = w4g;
    }

    public double getWwlan() {
        return wwlan;
    }

    public void setWwlan(double wwlan) {
        this.wwlan = wwlan;
    }

    public int getWhigh() {
        return whigh;
    }

    public void setWhigh(int whigh) {
        this.whigh = whigh;
    }

    public int getWmiddle() {
        return wmiddle;
    }

    public void setWmiddle(int wmiddle) {
        this.wmiddle = wmiddle;
    }

    public int getWlow() {
        return wlow;
    }

    public void setWlow(int wlow) {
        this.wlow = wlow;
    }

    public double getWfeatures() {
        return wfeatures;
    }

    public void setWfeatures(double wfeatures) {
        this.wfeatures = wfeatures;
    }

    public double getWview() {
        return wview;
    }

    public void setWview(double wview) {
        this.wview = wview;
    }

    public double getWdelay() {
        return wdelay;
    }

    public void setWdelay(double wdelay) {
        this.wdelay = wdelay;
    }

    public double getWconsume() {
        return wconsume;
    }

    public void setWconsume(double wconsume) {
        this.wconsume = wconsume;
    }

    public double getWexperience() {
        return wexperience;
    }

    public void setWexperience(double wexperience) {
        this.wexperience = wexperience;
    }
}
