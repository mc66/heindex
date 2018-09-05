package com.cmri.um.he.index.operations.service;

import com.cmri.um.he.index.operations.entity.AppOriginalOperationsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
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
    boolean saveAppOriginalContentEntity(List<AppOriginalOperationsEntity> list);
    /**
     *  新增或修改渠道、营销、资费、服务原始数据
     * @param list
     * @return
     */
    boolean updateAppOriginalContentEntity(List<AppOriginalOperationsEntity> list);

    /**
     * 读取Excel表
     * @param files
     * @return
     */
    String readExcelFile(List<MultipartFile> files);

    void exportCmtariffExcel();
}
