package com.cmri.um.he.index.operations.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能界面,原始数据对应的entity
 *
 * @author yinjunjun
 * Created on 2018/07/10
 */
public class AppCalculationOperationsEntity implements Serializable {
    private Integer id;
    private String month;
    private Integer app;
    private Integer category;
    private String version;
    private String content_cover;
    private String content_update;
    private String content_recommend;
    private Double content;
    private Double channel;
    private Double tariff;
    private Double service;
    private Double market;
    private Double experience;
    private Integer status;
    private Date atime;

    public AppCalculationOperationsEntity(Integer id, String month, Integer app, Integer category, String version, String content_cover, String content_update, String content_recommend, Double content, Double channel, Double tariff, Double service, Double market, Double experience, Integer status, Date atime) {
        this.id = id;
        this.month = month;
        this.app = app;
        this.category = category;
        this.version = version;
        this.content_cover = content_cover;
        this.content_update = content_update;
        this.content_recommend = content_recommend;
        this.content = content;
        this.channel = channel;
        this.tariff = tariff;
        this.service = service;
        this.market = market;
        this.experience = experience;
        this.status = status;
        this.atime = atime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent_cover() {
        return content_cover;
    }

    public void setContent_cover(String content_cover) {
        this.content_cover = content_cover;
    }

    public String getContent_update() {
        return content_update;
    }

    public void setContent_update(String content_update) {
        this.content_update = content_update;
    }

    public String getContent_recommend() {
        return content_recommend;
    }

    public void setContent_recommend(String content_recommend) {
        this.content_recommend = content_recommend;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }

    public AppCalculationOperationsEntity() {

    }

    @Override
    public String toString() {
        return "AppCalculationOperationsEntity{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", app=" + app +
                ", category=" + category +
                ", version='" + version + '\'' +
                ", content_cover='" + content_cover + '\'' +
                ", content_update='" + content_update + '\'' +
                ", content_recommend='" + content_recommend + '\'' +
                ", content=" + content +
                ", channel=" + channel +
                ", tariff=" + tariff +
                ", service=" + service +
                ", market=" + market +
                ", experience=" + experience +
                ", status=" + status +
                ", atime=" + atime +
                '}';
    }
}
