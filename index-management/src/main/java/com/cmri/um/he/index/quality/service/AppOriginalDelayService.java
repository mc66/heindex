package com.cmri.um.he.index.quality.service;

import com.cmri.um.he.index.quality.entity.AppOriginalDelayEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 品质得分中时延、功耗
 * @author lch
 * Created on 2018/06/27 09:55
 */
@Service
public interface AppOriginalDelayService {

    /**
     * 根据应用类别查询app
     * @param category
     * @return
     */
    List<Map<String, Object>> findAppByCategory(Integer category);

    /**
     * 批量新增时延功耗原始数据
     * @param list
     * @return
     */
    boolean saveAppOriginalDelayList(List<AppOriginalDelayEntity> list);

    /**
     * 处理延时功耗数据，存入app_calculation_quality表
     * @param list
     * @return
     */
    boolean dealAppOriginalDelayList(List<AppOriginalDelayEntity> list);
}
