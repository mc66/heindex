package com.cmri.um.he.index.service;

import com.cmri.um.he.index.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.entity.AppOriginalFeaturesEntity;

import java.util.List;

public interface AppOrginalFeaturesService {
    boolean saveList(List<AppOriginalFeaturesEntity> list);
    AppCalculationQualityEntity getScore(List<AppOriginalFeaturesEntity> list);
}
