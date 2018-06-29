package com.cmri.um.he.index.service;

import java.util.List;
import java.util.Map;
/**
 * 查询某类未添加应用体验接口的服务类
 *
 * @author Limin
 * Created on 2018/6/13
 */
public interface AppOriginalExperienceService {

    /**
     *  查询所有的体验数据
     * @return
     */
    List<Map<String,Object>> findWhole();

    /**
     *  is_valid
     * @param category
     * @return
     */
    List<Map<String,Object>> find( Integer category);
    /**
     *修改体验数据
     * @param id
     * @param experience
     * @return
     */
    int updateExperience(Integer id, Double experience);
}
