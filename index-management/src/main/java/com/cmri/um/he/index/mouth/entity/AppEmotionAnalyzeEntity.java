package com.cmri.um.he.index.mouth.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 	口碑指数原始数据(评论)
 * @author shihao
 * Created on 2018/8/6
 */
public class AppEmotionAnalyzeEntity implements Serializable {

    private int id;
    private int app;
    private int category;
    private String month;
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
    private String commentAttribute;
    /**
     *  用户评分
     */
    private int userScore;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 评论内容
     */
    private String comment;
    /**
     * 情感倾向（1：正，0：中,-1:负）
     */
    private int status;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
        return commentAttribute;
    }

    public void setCommentAttribute(String commentAttribute) {
        this.commentAttribute = commentAttribute;
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
