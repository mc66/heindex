package com.cmri.um.he.index.monument.entity;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
/**
 * 	口碑指数原始数据(评论)
 *
 * @author shihao
 * Created on 2018/8/6
 */
public class AppEmotionAnalyzeEntity implements Serializable {

    private int id;
    private String month;
    private int app;
    private int category;
    /**
     *  评论来源
     */
    private String commentSource;
    /**
     * app实际名称
     */
    private String appName;
    /**
     * 评论属性
     */
    private String CommentAttribute;
    /**
     *  用户评分
     */
    private int userScore;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 评论发表时间
     */
    private String commentTime;
    /**
     * 评论内容
     */
    private String comment;
    /**
     * 情感倾向（1：正，0：中,-1:负）
     */
    private int status;
    private Date atime;

    /**
     * 日正性评论数
     */
    @Transient
    private int freqPositive;
    /**
     * 日负性评论数
     */
    @Transient
    private int freqNegativity;
    /**
     * 日中性评论数
     */
    @Transient
    private int freqNeutral;

    /**
     * 日正性评论数所占百分比
     */
    @Transient
    private String petPositive;
    /**
     * 日负性评论数所占百分比
     */
    @Transient
    private String petNegativity;
    /**
     * 日中性评论数所占百分比
     */
    @Transient
    private String petNeutral;

    public int getFreqPositive() {
        return freqPositive;
    }

    public void setFreqPositive(int freqPositive) {
        this.freqPositive = freqPositive;
    }

    public int getFreqNegativity() {
        return freqNegativity;
    }

    public void setFreqNegativity(int freqNegativity) {
        this.freqNegativity = freqNegativity;
    }

    public int getFreqNeutral() {
        return freqNeutral;
    }

    public void setFreqNeutral(int freqNeutral) {
        this.freqNeutral = freqNeutral;
    }

    public String getPetPositive() {
        return petPositive;
    }

    public void setPetPositive(String petPositive) {
        this.petPositive = petPositive;
    }

    public String getPetNegativity() {
        return petNegativity;
    }

    public void setPetNegativity(String petNegativity) {
        this.petNegativity = petNegativity;
    }

    public String getPetNeutral() {
        return petNeutral;
    }

    public void setPetNeutral(String petNeutral) {
        this.petNeutral = petNeutral;
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

    public String getCommentSource() {
        return commentSource;
    }

    public void setCommentSource(String commentSource) {
        this.commentSource = commentSource;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCommentAttribute() {
        return CommentAttribute;
    }

    public void setCommentAttribute(String commentAttribute) {
        CommentAttribute = commentAttribute;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }
}
