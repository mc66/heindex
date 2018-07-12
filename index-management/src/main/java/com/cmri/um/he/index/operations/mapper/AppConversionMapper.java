package com.cmri.um.he.index.operations.mapper;

import com.cmri.um.he.index.operations.entity.AppOperationsEntity;
import com.cmri.um.he.index.operations.provider.AppOperationdDaoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 将计算值转换为标准值的sql配置
 *
 * @author yinjunjun
 * Created on 2018/07/10
 */
@Mapper
public interface AppConversionMapper {
    /**
     * 查询没有被处理过的数据
     * @param month 指定月份
     * @param category 指定类型
     * @return 未处理数据集合
     * */
    @Select("SELECT acq.id,acq.content,acq.channel,acq.tariff,acq.service,acq.market,acq.experience,acq.version,acq.`month`,acq.atime,info.id app,info.`name`,info.special,cate.id category,cate.`name` categoryName \n" +
            "    FROM app_calculation_operations acq LEFT JOIN app_info info on acq.app = info.id\n" +
            "  LEFT JOIN app_category cate ON acq.category = cate.id\n" +
            "WHERE acq.`status` = 0 AND acq.category=#{category} AND acq.`month` = #{month}")
    List<Map<String,Object>> queryUnConversion(@Param("category") Integer category,@Param("month") String month);

    /**
     * 将转化之后的数据批量加入qpp_operatios表
     * @param appOperationsEntityList 批量处理的集合
     * */
    @InsertProvider(type = AppOperationdDaoProvider.class,method = "saveAll")
    void saveAll(@Param("list") List<AppOperationsEntity> appOperationsEntityList);

    /**
     * 转化处理成功之后的状态
     * @param category 指定类别
     * @param month 指定月份
     * */
    @Update("UPDATE app_calculation_operations set `status`=1 WHERE category=#{category} AND `month`=#{month}")
    void UpdateStatus(@Param("category") Integer category,@Param("month") String month);

    /**
     * 查询所有的月份
     * @return 所有月份的集合
     * */
    @Select("SELECT `month` from app_calculation_operations GROUP BY `month` DESC ")
    List<Map<String,Object>> findMonths();

    @Select("SELECT name,category from app_info ")
    List<Map<String,Object>> ceshi();

}
