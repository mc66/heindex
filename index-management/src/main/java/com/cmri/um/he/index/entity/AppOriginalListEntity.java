package com.cmri.um.he.index.entity;


import java.io.Serializable;
import java.util.List;
/**
 * 用集合接受对象信息
 *
 * @author shihao
 * Created on 2018/6/22
 */
public class AppOriginalListEntity implements Serializable {

    private List<AppOriginalDelayEntity> appOrgDellist;
    private List<AppOriginalFeaturesEntity> appOrgFealist;

    public List<AppOriginalDelayEntity> getAppOrgDellist() {
        return appOrgDellist;
    }

    public void setAppOrgDellist(List<AppOriginalDelayEntity> appOrgDellist) {
        this.appOrgDellist = appOrgDellist;
    }

    public List<AppOriginalFeaturesEntity> getAppOrgFealist() {
        return appOrgFealist;
    }

    public void setAppOrgFealist(List<AppOriginalFeaturesEntity> appOrgFealist) {
        this.appOrgFealist = appOrgFealist;
    }
}
