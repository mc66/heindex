package com.cmri.um.he.index.appview.service;

import java.util.List;
import java.util.Map;

/**
 * 所有报告期数查询的服务类
 *
 * @author yinjunjun
 * Created on 2018/6/15
 */
public interface AppMonthService {
    /**
     * 查询所有报告期数
     * @return 查询结果
     */
    List<Map<String,Object>> findMonths();
}
