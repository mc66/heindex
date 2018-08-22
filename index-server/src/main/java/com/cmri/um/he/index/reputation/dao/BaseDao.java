package com.cmri.um.he.index.reputation.dao;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 数据库访问基类
 *
 * @author zhuyin
 * Created on 2018/6/4
 */
class BaseDao implements Serializable {
    final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 用于计算分页查询的offset
     *
     * @param page 所要查询的分页，从1开始
     * @param step 每页的记录容量
     * @return sql查询的offset
     */
    int offset(int page, int step) {
        Validate.isTrue(page > 0, "page must be bigger than 0");
        return (page - 1) * step;
    }
}
