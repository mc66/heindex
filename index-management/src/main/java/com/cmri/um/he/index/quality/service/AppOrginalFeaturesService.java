package com.cmri.um.he.index.quality.service;

import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppOriginalFeaturesEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * @author shihao
 * Created on 2018/6/24
 */
public interface AppOrginalFeaturesService {
    boolean saveList(List<AppOriginalFeaturesEntity> list);
    AppCalculationQualityEntity getScore(List<AppOriginalFeaturesEntity> list);

    /**
     * 读取excel中的数据,生成list
     */
    List readExcelFile(MultipartFile file);
}
