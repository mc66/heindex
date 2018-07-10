package com.cmri.um.he.index.quality.provider;

import com.cmri.um.he.index.quality.entity.AppQualityEntity;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
/**
 * 批量添加的数据库访问
 * @author yinjunjun
 * Created on 2018/06/28
 * */
public class AppCalculationDaoProvider {
    public String saveAll(Map map) {
        List<AppQualityEntity> appQualityEntities = (List<AppQualityEntity>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO app_quality ");
        sb.append("(id,app,category,features,views,delay,consume,experience,qindex,version,month,atime) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].app},#'{'list[{0}].category},#'{'list[{0}].features},#'{'list[{0}].views},#'{'list[{0}].delay},#'{'list[{0}].consume},#'{'list[{0}].experience},null,#'{'list[{0}].version},#'{'list[{0}].month},#'{'list[{0}].atime})");
        for (int i = 0; i < appQualityEntities.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < appQualityEntities.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
