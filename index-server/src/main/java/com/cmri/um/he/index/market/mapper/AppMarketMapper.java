package com.cmri.um.he.index.market.mapper;

import com.cmri.um.he.index.market.entity.AppMarketEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppMarketMapper {

    @Select("SELECT ai.`name`,am.`mau_number`,am.`keep_rate`,am.`length_time`,am.`flow`,am.`market`,ai.`flag`\n" +
            "FROM app_market am JOIN app_info ai ON am.`app`=ai.`id`\n" +
            "WHERE am.`category`=#{category} AND am.`month`=#{month}")
    public List<Map<String,Object>> getAppMarketList(@Param("category") int category, @Param("month") String month);

    @Select("SELECT m.id '编号', a.`name` 名称,m.mau_number '月均活跃用户数', \n" +
            "m.keep_rate '次月留存率',m. length_time  '月人均使用时长',m.flow  '月人均使用流量'\n" +
            "from app_market m ,app_info a where m.app=a.id AND  m.category=#{category} AND m.month BETWEEN #{month2} AND #{month}")
    List<Map<String, Object>> getMarket(@Param("category") int category, @Param("month")String month,@Param("month2")String month2);
}
