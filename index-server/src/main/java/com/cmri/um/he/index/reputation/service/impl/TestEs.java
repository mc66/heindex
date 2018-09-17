package com.cmri.um.he.index.reputation.service.impl;

import com.cmri.um.he.index.reputation.entity.EmotionAnalyzeResult;
import com.cmri.um.he.index.reputation.entity.SaveEmotionAnalyze;

/**
 * Created by machao on 2018/9/13.
 */
public interface TestEs {

    public void saveEmotion(SaveEmotionAnalyze saveEmotionAnalyze);

    //public EmotionAnalyzeResult searchSkuInfoList(SkuLsParam skuLsParam);
}
