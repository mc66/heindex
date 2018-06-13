package com.cmri.um.he.index.dao;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.mapper.AppFeaturesMapper;
import com.cmri.um.he.index.mapper.AppMauMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 应用的关键点的数据库访问
 *
 * @author Limin
 * Created on 2018/6/13
 */
@Repository
public class AppFeaturesDao extends BaseDao {

    @Autowired
    private AppFeaturesMapper appFeaturesMapper;



    /**
     *  查询指定月份、应用名的应用的关键点数据的个数
     */
    public int countOfApp(String month, int app) {
        return appFeaturesMapper.countOfApp(month, app);
    }


    /**
     *  分页查询指定月份、应用名的应用的关键点数据
     */
    public List<Map<String, Object>> findByApp(String month, int app, int page, int step) {
        return addNo(appFeaturesMapper.findByApp(month, app, offset(page, step), step));
    }

    /**
     *  为记录添加序号
     */
    private List<Map<String, Object>> addNo(List<Map<String, Object>> items) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).put("no", i);
        }
        return items;
    }

}
