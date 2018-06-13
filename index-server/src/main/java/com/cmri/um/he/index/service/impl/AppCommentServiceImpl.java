package com.cmri.um.he.index.service.impl;

import com.cmri.spring.common.data.PagingData;
import com.cmri.um.he.index.dao.AppCommentDao;
import com.cmri.um.he.index.service.AppCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 应用评论查询的服务类实现
 * @author lch
 * Created on 2018/06/13 14:30
 */
@Service
public class AppCommentServiceImpl implements AppCommentService{
    @Autowired
    private AppCommentDao appCommentDao;

    @Override
    public PagingData<Map<String, Object>> find(String month, String category, int page, int step) {
        return new PagingData<>(appCommentDao.countOfCategory(month, category),
                page,
                step,
                appCommentDao.findByCategory(month, category, page, step)
        );
    }
}
