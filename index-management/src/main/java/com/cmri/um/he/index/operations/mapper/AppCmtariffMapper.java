package com.cmri.um.he.index.operations.mapper;

import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.entity.AppOriginalOperationsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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
    @Insert(" insert into app_original_operations values (#{id},#{month},#{category},#{app},#{version},#{measureIndex},#{specificChannel},#{serverFrom},#{measureValue},#{dimensions},#{explain},#{atime}) ")
    int saveAppOriginalContentEntity(AppOriginalOperationsEntity entity);

    /**
     *  查询每个维度名称记1的数量
     * @param dimensions
     * @return
     */
    @Select(" SELECT count(*) FROM app_original_operations ao WHERE ao.measure_value=1 AND ao.dimensions=#{dim} AND ao.app=#{app} AND ao.month=#{month}")
    int queryAppCalculationOperationsEntityByMeasureValue(@Param("dim") String dimensions,@Param("app") int app,@Param("month") String month);

    /**
     *  查询所有的条数
     * @param dimensions
     * @return
     */
    @Select(" SELECT count(*) FROM app_original_operations  WHERE dimensions=#{dim} AND app=#{app} AND month=#{month}")
    int queryAppCalculationOperationsEntityByDimensionsId( @Param("dim") String dimensions,@Param("app") int app,@Param("month") String month);

    /**
     *  查询 app_calculation_operations是否有数据
     * @param app
     * @param month
     * @return
     */
    @Select(" SELECT * FROM app_calculation_operations ap WHERE ap.app=#{app} and ap.`month`=#{month}")
    List<Map<String,Object>> queryAppCalculationOperations(@Param("app") int app,@Param("month") String month);

    /**
     *  新增 这个app_calculation_operations表
     * @param aco
     * @return
     */
    @Insert("INSERT INTO app_calculation_operations values(#{id},#{month},#{app},#{category},#{version},#{content},#{channel},#{tariff},#{service},#{market},#{experience},#{status},#{atime})")
    int saveAppCalculationOperations(AppCalculationOperationsEntity aco);

    /**
     *  修改 这个app_calculation_operations表
     * @param channel
     * @param market
     * @param month
     * @param service
     * @param tariff
     * @param app
     * @return
     */
    @Update(" UPDATE app_calculation_operations aca SET aca.channel=#{channel},aca.market=#{market},aca.service=#{service},aca.tariff=#{tariff} WHERE aca.app=#{app} AND aca.`month`=#{month}")
    int updateAppCalculationOperations(@Param("channel") Double channel,@Param("market") Double market,@Param("month") String month,@Param("service") Double service,@Param("tariff") Double tariff,@Param("app") int app);

}
