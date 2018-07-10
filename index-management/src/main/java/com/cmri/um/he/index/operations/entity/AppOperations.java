package com.cmri.um.he.index.operations.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 功能界面,标准值数据对应的entity
 *
 * @author yinjunjun
 * Created on 2018/07/10
 */
public class AppOperations implements Serializable {
    private Integer id;
    private Integer app;
    private Integer category;
    private Double content;
    private Double channel;
    private Double tariff;
    private Double service;
    private Double market;
    private Double experience;
    private Double oindex;
    private String version;
    private String month;
    private Date atime;

    @Override
    public String toString() {
        return "AppOperations{" +
                "id=" + id +
                ", app=" + app +
                ", category=" + category +
                ", content=" + content +
                ", channel=" + channel +
                ", tariff=" + tariff +
                ", service=" + service +
                ", market=" + market +
                ", experience=" + experience +
                ", oindex=" + oindex +
                ", version='" + version + '\'' +
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

    public Double getContent() {
        return content;
    }

    public void setContent(Double content) {
        this.content = content;
    }

    public Double getChannel() {
        return channel;
    }

    public void setChannel(Double channel) {
        this.channel = channel;
    }

    public Double getTariff() {
        return tariff;
    }

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    public Double getService() {
        return service;
    }

    public void setService(Double service) {
        this.service = service;
    }

    public Double getMarket() {
        return market;
    }

    public void setMarket(Double market) {
        this.market = market;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Double getOindex() {
        return oindex;
    }

    public void setOindex(Double oindex) {
        this.oindex = oindex;
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

    public AppOperations() {

    }

    public AppOperations(Integer id, Integer app, Integer category, Double content, Double channel, Double tariff, Double service, Double market, Double experience, Double oindex, String version, String month, Date atime) {

        this.id = id;
        this.app = app;
        this.category = category;
        this.content = content;
        this.channel = channel;
        this.tariff = tariff;
        this.service = service;
        this.market = market;
        this.experience = experience;
        this.oindex = oindex;
        this.version = version;
        this.month = month;
        this.atime = atime;
    }
}
