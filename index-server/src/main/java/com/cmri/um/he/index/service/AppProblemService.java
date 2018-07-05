package com.cmri.um.he.index.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询app某维度的问题
 * @author machao
 * Created on 2018/7/4
 */
public interface AppProblemService {
    /**
     * 查询app某一个维度产品品质问题的方法
     * @param id
     * @param dimensions
     * @param month
     * @return
     */
    public List<Map<String, Object>> getAppQualityProblem(int id,String dimensions,String month);

    /**
     * 查询app某一个维度产品运营问题的方法
     * @param id
     * @param dimensions
     * @param month
     * @return
     */
    public List<Map<String, Object>> getAppOperationProblem(int id,String dimensions,String month);


}
