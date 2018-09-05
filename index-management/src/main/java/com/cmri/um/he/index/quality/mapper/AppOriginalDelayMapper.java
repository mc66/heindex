package com.cmri.um.he.index.quality.mapper;

import com.cmri.um.he.index.quality.entity.AppCalculationQualityEntity;
import com.cmri.um.he.index.quality.entity.AppOriginalDelayEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 品质得分中时延、功耗的sql配置
 * @author lch
 * Created on 2018/06/27 09:55
 */
@Mapper
public interface AppOriginalDelayMapper {

    /**
     * 查询应用类别
     * @param category
     * @return 应用类别集合
     */
    @Select("SELECT app.id,app.`name`,app.flag,app.special FROM app_info app WHERE app.category=#{category} ORDER BY app.id")
    List<Map<String,Object>> findAppByCategory(Integer category);

    /**
     * 延时功耗原始数据入库
     * @param appOriginalDelayEntity
     * @return
     */
    @Insert("insert into app_original_delay values (#{id},#{app},#{category},#{version},#{measuring},#{network}," +
            "#{measure},#{standard},#{challenge},#{status},#{month},#{atime})")
    int saveOriginal(AppOriginalDelayEntity appOriginalDelayEntity);

    /**
     * 延时功耗计算值入库
     * @param entity
     * @return
     */
    @Insert("INSERT INTO app_calculation_quality VALUES (#{id},#{app},#{category},#{features},#{view},#{delay}," +
            "#{consume},#{experience},#{status},#{version},#{month},#{atime})")
    int saveDelay(AppCalculationQualityEntity entity);

    /**
     * 延时功耗计算值入库
     * @param entity
     * @return
     */
    @Update("update app_calculation_quality set delay=#{delay},consume=#{consume},atime=#{atime} where app=#{app} and month=#{month}")
    int updateDelay(AppCalculationQualityEntity entity);
}
