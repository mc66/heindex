package com.cmri.um.he.index.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询app某一具体维度的问题列表
 * Created by machao on 2018/7/3.
 */
public interface AppProblemService {
    /**
     * 查询app某一个维度问题点方法
     * @param app
     * @param dimensions
     * @return
     */
    public List<Map<String, Object>> getAppProblem(int app,String dimensions,String month);
}
