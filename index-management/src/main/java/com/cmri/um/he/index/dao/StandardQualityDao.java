package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.StandardQualityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 未处理为标准值的数据库访问
 *
 * @author yinjunjun
 * Created on 2018/6/25
 */
@Repository
public class StandardQualityDao extends BaseDao {

    @Autowired
    private StandardQualityMapper mapper;

    /**
     * 查询未处理为标准值的数据
     *
     * */
    public List<Map<String, Object>> unprocessedStandard(Integer category,String month) {
        return mapper.unprocessedStandard(category,month);
    }

    /**
     * 处理成功后改变状态
     * */
    public void updateStatus(Integer category,String month){
        mapper.updateStatus(category, month);
    }
}
