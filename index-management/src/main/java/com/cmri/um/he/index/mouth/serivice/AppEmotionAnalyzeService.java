package com.cmri.um.he.index.mouth.serivice;


import com.cmri.um.he.index.mouth.entity.AppEmotionAnalyzeEntity;

import java.util.List;
/**
 * 口碑指数原始数据录入
 * @author shihao
 * Created on 2018/8/14
 */
public interface AppEmotionAnalyzeService {

    Boolean saveMouth(List<AppEmotionAnalyzeEntity> list);
    Boolean  getEmotionScore();
}
