package com.cmri.um.he.index.operations.mapper;

import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import org.apache.ibatis.annotations.*;
/**
 * 运营指数中内容得分
 * @author shihao
 * Created on 2018/7/12
 */
@Mapper
public interface AppCalculationOperationsMapper {

    /**
     * 根据app查询是否存在内容得分
     * @param app
     * @return
     */
    @Select("SELECT content from app_calculation_operations where app=#{app} AND month=#{month}")
    String getDataByApp(@Param("app") int app,@Param("month") String month);


    /**
     * 添加计算得到的内容得分
     * @param appCalculationOperationsEntity
     * @return
     */
    @Insert("INSERT INTO app_calculation_operations values(#{id},#{month},#{app},#{category},#{version},#{content},#{channel}," +
            "#{tariff},#{service},#{market},#{experience},#{status},#{atime})")
    int saveContentValue(AppCalculationOperationsEntity appCalculationOperationsEntity);

    /**
     * 修改内容得分
     * @param content
     * @param app
     * @param month
     * @return
     */
    @Update("UPDATE app_calculation_operations set content=#{content} WHERE app=#{app} AND month=#{month}")
    int updateContentValue(@Param("content") double content,@Param("app") int app,@Param("month") String month);
}
