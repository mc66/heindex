package com.cmri.um.he.index.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppMarketGeneralMapper {

    /**
     * 查询次月存留率
     * @return
     */
    @Select("select category,app, month ,mau_number AS '月活跃用户',keep_rate AS '次月存留率' from app_market \n" +
            "where app=60 and category=3 and month BETWEEN 201801 and 201812 ORDER BY month\n")
    List<Map<String, Object>> test();

    /**
     * 查存月活跃用户
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @Select("SELECT m.app , m.new_user ,m.mau_number,m.month\n" +
            "from app_market m\n" +
            "where m.app=#{app} and m.month BETWEEN #{month1} and #{month2} ORDER BY m.month")
    List<Map<String, Object>> getUserNumber(@Param("app") Integer app,@Param("month1") String  month1,@Param("month2") String  month2 );
}
