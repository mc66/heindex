package com.cmri.um.he.index.operations.service;

/**
 * excel导出
 * @author lch
 * Created on 2018/08/31 16:39
 */
public interface AppOperationsExcelExportService {
    void exportQualityExcel();

    /**
     * 体验指数-产品运营-导出内容得分方法
     */
    void exportContentScore(String month);
}
