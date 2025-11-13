package com.ruoyi.starters.mybatisflex;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ruoyi.mybatis-flex")
public class MybatisFlexProperties {
    private String mapperBasePackages = "com.ruoyi.**.mapper";

    public String getMapperBasePackages() {
        return mapperBasePackages;
    }

    public void setMapperBasePackages(String mapperBasePackages) {
        this.mapperBasePackages = mapperBasePackages;
    }
}

