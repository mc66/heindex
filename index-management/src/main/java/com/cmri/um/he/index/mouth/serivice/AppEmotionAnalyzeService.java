package com.cmri.um.he.index.mouth.serivice;


import com.cmri.um.he.index.mouth.entity.AppEmotionAnalyzeEntity;

import java.util.List;

public interface AppEmotionAnalyzeService {

    Boolean saveMouth(List<AppEmotionAnalyzeEntity> list);
    Boolean  getEmotionScore();
}
