package com.mininglamp.service.index.mapper;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("tb_demo")
public class Demo {
    private Integer id;
    private String name;
    private String description;
    private Integer age;
}