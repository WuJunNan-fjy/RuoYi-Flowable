package com.ruoyi.starters.mybatisplus.demo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.starters.mybatisplus.base.BaseEntityX;

@TableName("t_demo")
public class DemoDO extends BaseEntityX {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String code;
    private String name;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}