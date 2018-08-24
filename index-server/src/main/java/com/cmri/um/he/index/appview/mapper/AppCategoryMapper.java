package com.cmri.um.he.index.appview.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 应用类别数据库访问的sql配置
 * Created  on 2018/6/12.
 * @author lch
 */
@Mapper
public interface AppCategoryMapper {
    /**
     * 查询应用类别
     * @return 应用类别集合
     */
    @Select("SELECT id, name FROM app_category WHERE `status`=1 order by id")
    List<Map<String,Object>> findCategory();

    /**
     * 查询所有应用类别(不带id为0的全部)
     */
    @Select("SELECT id, name FROM app_category where id!=0 order by id")
    List<Map<String,Object>> getCategory();

    /**
     * 查询应用类别及对应的app数据
     * @return 应用类别集合
     */
    @Select("SELECT  info.id app_id , category.`name` cate,category.id category_id,info.`name`,CONCAT(category.`name`,'报告') AS newcal\n" +
            "from app_category category \n" +
            "LEFT JOIN app_info info ON category.id = info.category\n" +
            "WHERE info.flag = 1;")
    List<Map<String,Object>> findCategorys();

    /**
     * 查询应用类别
     * @param category
     * @return 应用类别集合
     */
    @Select("SELECT app.id,app.`name`,app.flag FROM app_info app WHERE app.category=#{category} ORDER BY app.id")
    List<Map<String,Object>> findAppByCategory(Integer category);

    @Select("SELECT  DISTINCT e.app ,a.name,a.flag from app_emotion_analyze e ,app_info a  where e.app=a.id ANd e.category=#{category}")
    List<Map<String,Object>> getCategoryByEmotion(Integer category);
}
