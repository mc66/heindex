package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.QualityMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 报告期数的数据库访问
 *
 * @author yinjunjun
 * Created on 2018/6/15
 */
@Repository
public class QualityMonthDao extends BaseDao{
    @Autowired
    private QualityMonthMapper qualityMonthMapper;

    /**
     * 查询所有报告期数
     * */
    public List<Map<String,Object>> findMonths(){
        return qualityMonthMapper.findMonths();
    }
}
