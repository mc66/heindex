package com.cmri.um.he.index.reputation.entity;

import java.util.List;

/**
 * Created by machao on 2018/9/14.
 */
public class EmotionAnalyzeResult {

    List<SaveEmotionAnalyze> saveEmotionAnalyzeList;

    //返回的数据个数
    int Total;

    public List<SaveEmotionAnalyze> getSaveEmotionAnalyzeList() {
        return saveEmotionAnalyzeList;
    }

    public void setSaveEmotionAnalyzeList(List<SaveEmotionAnalyze> saveEmotionAnalyzeList) {
        this.saveEmotionAnalyzeList = saveEmotionAnalyzeList;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }
}
