package com.cmri.um.he.index.reputation.entity;

import java.io.Serializable;

/**
 * Created by machao on 2018/9/13.
 */
public class SaveEmotionAnalyze implements Serializable{

    Integer appId;
    String appName;
    String comment;
    String userName;
    String month;
    String commentSource;
    Integer status;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
