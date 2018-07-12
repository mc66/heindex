package com.cmri.um.he.index.operations.mapper;

import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
/**
 * 	渠道、营销、资费、服务原始数据
 *
 * @author limin
 * Created on 2018/7/10
 */
@Mapper
public interface AppCmtariffMapper {


    /**
     *  批量新增渠道、营销、资费、服务原始数据
     * @param entity
     * @return
     */
    @Insert(" insert into app_original_operations values (#{id},#{month},#{category},#{app},#{version},#{measure_index},#{specific_channel},#{server_from},#{measure_value},#{explain},#{dimensions_id},#{atime}) ")
    int saveAppOriginalContentEntity(AppOriginalContentEntity entity);
}
