package com.cmri.um.he.index.operations.service;

import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;

import java.util.List;
import java.util.Map;
/**
 * 运营指数中内容得分
 * @author shihao
 * Created on 2018/7/11
 */
public interface AppOriginalContentService {
    boolean saveContent(List<AppOriginalContentEntity> list);
    List<Map<String,Object>> getContent();
}
