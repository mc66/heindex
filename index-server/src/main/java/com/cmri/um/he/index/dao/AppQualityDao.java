package com.cmri.um.he.index.dao;

import com.cmri.um.he.index.mapper.AppQualityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* 查询某类应用品质指数
* @author shihao
* Created on 2018/6/13
*/
@Repository
public class AppQualityDao extends BaseDao {
    @Autowired
    private AppQualityMapper qumapper;

    /**
     * 查询指定月份、指定类别的应用应用数
     */
    public int getCount(String month, String category) {
        return qumapper.getCount(month, category);
    }

    /**
     * 分页查询指定月份、指定类别的应用应用品质指数
     */
    public List<Map<String, Object>> getQualityList(String month, String category, int page, int step) {
        return addNo(qumapper.getQualityList(month, category, offset(page, step), step));
    }

    /**
     * 为记录添加序号
     */
    public List<Map<String, Object>> addNo(List<Map<String, Object>> items) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).put("no", i);
        }
        return items;
    }

    /**
     * 查询单个app及月份的数据
     */
    public List<Map<String, Object>> getQualityData(int id, String month2,String month){
       List resultList= qumapper.getQualityData(id,month2,month);
       return  resultList;
    }

    /**
     *   查询功能指标
     * @param app
     * @param month
     * @return
     */
    public List<Map<String, Object>> queryQualityFeatures(Integer app, String month) {
        return  qumapper.queryQualityFeatures(app,month);
    }

    /**
     *    查询界面指标
     * @param app
     * @param month
     * @return
     */
    public List<Map<String,Object>> queryQualityViews(Integer app, String month) {
        return  qumapper.queryQualityViews(app,month);
    }

    /**
     *   查询时延指标
     * @param app
     * @param month
     * @return
     */
    public List<Map<String,Object>> queryQualityDelay(Integer app, String month) {
        return  qumapper.queryQualityDelay(app,month);
    }

    /**
     *   查询功耗指标
     * @param app
     * @param month
     * @return
     */
    public List<Map<String,Object>> queryQualityConsume(Integer app, String month) {
        return  qumapper.queryQualityConsume(app,month);
    }

    /**
     *   查询使用体验
     * @param id
     * @param month
     * @return
     */
    public List<Map<String,Object>> queryQualityExperience(Integer id, String month) {
        return  qumapper.queryQualityExperience(id,month);
    }

    /**
     * 查询app的logo
     * @param appId
     * @return
     */
    public List<Map<String,Object>> getAppLogo(int appId){
        return qumapper.getAppLogo(appId);
    }
}
