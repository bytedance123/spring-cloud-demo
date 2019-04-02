package com.mininglamp.service.index.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SqlMapper {
    @Select("select id,name,age from tb_demo where id =#{id}")
    @ResultType(Map.class)
    List<Map> list(Map map);
}