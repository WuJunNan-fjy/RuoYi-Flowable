package com.ruoyi.starters.mybatisplus;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "ruoyi.mybatis-plus")
public class MybatisPlusProperties {
    private String mapperBasePackages = "com.ruoyi.**.mapper";

    public String getMapperBasePackages() {
        return mapperBasePackages;
    }

    public void setMapperBasePackages(String mapperBasePackages) {
        this.mapperBasePackages = mapperBasePackages;
    }
}
