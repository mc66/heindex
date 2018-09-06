package com.cmri.um.he.index.appview.dao;

import com.cmri.um.he.index.appview.mapper.AppOwnDescMapper;
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

    /**
     * 查询指定id，指定月份的运营分数及描述
     * */
    public List<Map<String,Object>> findByIdAndMonth(Integer id,String month){
        return mapper.findByIdAndMonth(id, month);
    }

    /**
     * 查询指定id，指定月份产品品质中的产品简介
     * */
    public List<Map<String,Object>> findAppInfo1ByIdAndMonth(Integer id,String month){
        return mapper.findAppInfo1ByIdAndMonth(id,month);
    }

    /**
     * 查询指定id，指定月份产品运营中的产品简介
     * */
    public List<Map<String,Object>> findAppInfo2ByIdAndMonth(Integer id,String month){
        return mapper.findAppInfo2ByIdAndMonth(id,month);
    }

}
