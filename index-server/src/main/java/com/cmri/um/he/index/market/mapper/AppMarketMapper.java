package com.cmri.um.he.index.market.mapper;

import com.cmri.um.he.index.market.entity.AppMarketEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppMarketMapper {

    @Select("SELECT ai.`name`,am.`mau_number`,am.`keep_rate`,am.`length_time`,am.`flow`,am.`market`,ai.`flag`\n" +
            "FROM app_market am JOIN app_info ai ON am.`app`=ai.`id`\n" +
            "WHERE am.`category`=#{category} AND am.`month`=#{month}")
    public List<Map<String,Object>> getAppMarketList(@Param("category") int category, @Param("month") String month);

}
