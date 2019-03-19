package com.mininglamp.service.index.service;

import java.util.List;
import java.util.Map;

import com.mininglamp.service.index.mapper.Demo;
import com.mininglamp.service.index.mapper.DemoMapper;
import com.mininglamp.service.index.mapper.SqlMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService{

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private SqlMapper sqlMapper;

    public List<Demo> findList(){
        return demoMapper.selectList(null);
    }

    public List<Map> findListBySql(Map map){
        return sqlMapper.list(map);
    }

}