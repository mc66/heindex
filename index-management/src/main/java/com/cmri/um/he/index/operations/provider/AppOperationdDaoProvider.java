package com.cmri.um.he.index.operations.provider;

import com.cmri.um.he.index.operations.entity.AppOperationsEntity;
import com.cmri.um.he.index.quality.entity.AppQualityEntity;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 批量添加的数据库访问
 * @author yinjunjun
 * Created on 2018/07/10
 * */
public class AppOperationdDaoProvider {
    public String saveAll(Map map) {
        List<AppOperationsEntity> appOperationsEntities = (List<AppOperationsEntity>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO app_operations ");
        sb.append("(id,app,category,content,channel,tariff,service,market,experience,oindex,version,month,atime) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].app},#'{'list[{0}].category},#'{'list[{0}].content},#'{'list[{0}].channel},#'{'list[{0}].tariff},#'{'list[{0}].service},#'{'list[{0}].market},#'{'list[{0}].experience},#'{'list[{0}].oindex},#'{'list[{0}].version},#'{'list[{0}].month},#'{'list[{0}].atime})");
        for (int i = 0; i < appOperationsEntities.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < appOperationsEntities.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
