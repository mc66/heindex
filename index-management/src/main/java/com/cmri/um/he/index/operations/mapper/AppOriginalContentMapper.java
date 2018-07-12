package com.cmri.um.he.index.operations.mapper;

import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
/**
 * 运营指数中内容得分
 * @author shihao
 * Created on 2018/7/12
 */
@Mapper
public interface AppOriginalContentMapper {

    /**
     * 查询内容分类
     * @return
     */
    @Select("SElECT * from app_content_category")
    List<Map<String, Object>> getContent();

    /**
     * 添加内容数据
     * @param appOriginalContentEntity
     */
    @Insert("INSERT INTO app_original_content values (#{id},#{month},#{category},#{app},#{version},#{contentName}," +
            "#{contentHome},#{measuredIndex},#{measuredProblem},#{measuredValue},#{contentId},#{remarks},#{atime})")
    void saveContent(AppOriginalContentEntity appOriginalContentEntity);

    /**
     * 查得样本库大小
     * @param contentId
     * @return
     */
    @Select("SELECT count(*) from app_original_content WHERE content_id=#{contentId} AND app=#{app}")
    int getContentByContentId(@Param("contentId") int contentId,@Param("app") int app);

    /**
     * 查得测量值为1的数据数
     * @param contentId
     * @param measuredValue
     * @return
     */
    @Select("SELECT count(*) from app_original_content WHERE content_id=#{contentId} AND measured_value=#{measuredValue} AND app=#{app}")
    int getContentByContentIdAndMeasureValue(@Param("contentId") int contentId,@Param("measuredValue") int measuredValue,@Param("app") int app);
}
