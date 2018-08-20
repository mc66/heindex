package com.cmri.um.he.index.receivable;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * 查询评论详情接收参数VO
 * @author machao
 * Created on 2018/8/15
 */
public class CommentParticularsVO implements Serializable{
    /**
     * 产品id
     */
    private Integer app;
    /**
     * 查询区间开始时间
     */
    private String startTime;
    /**
     * 查询区间结束时间
     */
    private String endTime;
    /**
     * 热词
     */
    private String word;
    /**
     * 情感倾向
     */
    private Integer status;
    /**
     * 评论来源
     */
    private String commentSource;
    /**
     * 显示页码
     */
    private Integer page;

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    private Integer rows;

    private int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Integer getRows() {
        return rows;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    /**
     *每页显示条数
     */
    private Integer step;

    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCommentSource() {
        return commentSource;
    }

    public void setCommentSource(String commentSource) {
        this.commentSource = commentSource;
    }
}
