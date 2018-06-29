package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.entity.AppQualityEntity;
import com.cmri.um.he.index.mapper.AppQualityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 查询未计算体验指数的所有应用，计算应用的品质总分
 * @author machao
 * Created on 2018/6/28
 */
@Repository
public class AppQualityDao extends BaseDao {
    @Autowired
    private AppQualityMapper appQualityMapper;

    /**
     * 根据qindex是否为null查询所有未计算的应用
     */
    public List<Map<String, Object>> quaryQualityList(){
        return appQualityMapper.quaryQualityList();
    }


    /**
     * 将计算好的应用品质得分存入数据库
     * @param app
     * @param version
     * @param month
     * @param qindex
     * @return
     */
    public int setQindex(int app,String version,String month,double qindex){
        return appQualityMapper.setQindex(app,version,month,qindex);
    }

}
