package com.cmri.um.he.index.operations.service;
import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;

import java.util.List; /**
 * 	渠道、营销、资费、服务原始数据
 *
 * @author limin
 * Created on 2018/7/10
 */
public interface AppCmtariffService {

    /**
     *  批量新增渠道、营销、资费、服务原始数据
     * @param list
     * @return
     */
    boolean saveAppOriginalContentEntity(List<AppOriginalContentEntity> list);
    /**
     *  处理渠道、营销、资费、服务原始数据，存入app_calculation_operations表
     * @param list
     * @return
     */
    boolean dealAppOriginalContentEntity(List<AppOriginalContentEntity> list);
}
