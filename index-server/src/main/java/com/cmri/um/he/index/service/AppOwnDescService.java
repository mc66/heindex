package com.cmri.um.he.index.service;


import java.util.List;
import java.util.Map;

/**
 * 咪咕视频介绍以及所属类品质分数查询的服务类
 *
 * @author yinjunjun
 * Created on 2018/07/02
 */
public interface AppOwnDescService {

    /**
     * 查询指定app的id，指定月份的品质分数
     * @param id App的id
     * @param month 指定月份
     * @return 应用类品质分数集合
     * */
    List<Map<String,Object>> findByAppId(Integer id,String month);

    /**
     * 查询指定app的id，指定月份的运营分数
     * @param id
     * @param month
     * @return
     */
    List<Map<String,Object>> findByIdAndMonth(Integer id,String month);

}
