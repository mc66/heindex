package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.AppOperationsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 应用月活的数据库访问
 *
 * @author zhuyin
 * Created on 2018/6/4
 */
@Repository
public class AppOperationsDao extends BaseDao {
    @Autowired
    private AppOperationsMapper appOperationsMapper;

    /**
     * 查询指定月份、指定类别的应用数
     */
    public int countOfCategory(String month, String category) {
        return appOperationsMapper.countOfCategory(month, category);
    }

    /**
     * 分页查询指定月份、指定类别的应用的运营指数
     */
    public List<Map<String, Object>> getOperationsList(String month, String category, int page, int step) {
        return addNo(appOperationsMapper.getOperationsList(month, category, offset(page, step), step));
    }

    /**
     * 为记录添加序号
     */
    private List<Map<String, Object>> addNo(List<Map<String, Object>> items) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).put("no", i);
        }
        return items;
    }
    public List<Map<String, Object>>getOperationsData(int id,String month2,String month){
        List resultList= appOperationsMapper.getOperationsData(id,month2,month);
        return resultList;
    }

}
