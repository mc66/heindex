package com.cmri.um.he.index.service;

import com.cmri.um.he.index.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.entity.AppOriginalFeaturesEntity;

import java.util.List;
/**
 * @author shihao
 * Created on 2018/6/24
 */
public interface AppOrginalFeaturesService {
    boolean saveList(List<AppOriginalFeaturesEntity> list);
    AppCalculationQualityEntity getScore(List<AppOriginalFeaturesEntity> list);
}
