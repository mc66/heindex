package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.AppProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 查询app某维度的问题
 * @author machao
 * Created on 2018/7/4
 */
@Repository
public class AppProblemDao extends BaseDao{

    @Autowired
    AppProblemMapper appProblemMapper;

    /**
     * 查询app某一个维度产品品质问题的方法
     * @param id
     * @param dimensions
     * @param month
     * @return
     */
    public List<Map<String, Object>> getAppQualityProblem(int id,String dimensions,String month) {
        return appProblemMapper.getAppQualityProblem(id,dimensions,month);

    }

    /**
     * 查询app某一个维度产品运营问题的方法
     * @param id
     * @param dimensions
     * @param month
     * @return
     */
    public List<Map<String, Object>> getAppOperationProblem(int id,String dimensions,String month) {
        return appProblemMapper.getAppOperationProblem(id,dimensions,month);
    }


}
