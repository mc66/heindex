package com.cmri.um.he.index.mapper;

import com.cmri.um.he.index.entity.AppOriginalFeaturesEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shihao
 * Created on 2018/6/24
 */
@Mapper
public interface AppOriginalFeaturesMapper {

    @Insert("insert into app_original_features values (#{id},#{app},#{category},#{version},#{dimensions},#{des}," +
            "#{experience},#{frequency},#{score},#{degree},#{status},#{month},#{atime})")
    int saveList(AppOriginalFeaturesEntity appOriginalFeaturesEntity);

}
