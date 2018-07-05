package com.cmri.um.he.index.service;

import java.util.List;
import java.util.Map;

/**
 * 应用类别查询的服务类
 *
 * @author lch
 * Created on 2018/6/13
 */
public interface AppCategoryService {
    /**
     * 查询所有应用类别
     *
     * @return 应用类别集合
     */
    List<Map<String, Object>> find();

    /**
     * 查询应用类别及对应的app数据
     *
     * @return 查询应用类别及对应的app数据
     */
    List<Map<String, Object>> finds();
}
