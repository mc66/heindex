package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.AppOwnDescMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 咪咕视频介绍以及所属类品质分数的数据库访问
 *
 * @author yinjunjun
 * Created on 2018/07/02
 */
@Repository
public class AppOwnDescDao extends BaseDao {

    @Autowired
    private AppOwnDescMapper mapper;

    /**
     * 查询指定id，指定月份的品质分数及描述
     * */
    public List<Map<String,Object>> findByAppId(Integer id,String month){
        return mapper.findByAppId(id, month);
    }

}
