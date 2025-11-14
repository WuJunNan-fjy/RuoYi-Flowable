package com.ruoyi.starters.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class IdTypeEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final String ID_TYPE_KEY = "mybatis-plus.global-config.db-config.id-type";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        IdType idType = environment.getProperty(ID_TYPE_KEY, IdType.class);
        if (idType != null && idType != IdType.NONE) {
            return;
        }
        DbType dbType = getDbType(environment);
        if (dbType == null) {
            return;
        }
        if (dbType == DbType.ORACLE || dbType == DbType.ORACLE_12C || dbType == DbType.POSTGRE_SQL
                || dbType == DbType.KINGBASE_ES || dbType == DbType.DB2 || dbType == DbType.H2) {
            environment.getSystemProperties().put(ID_TYPE_KEY, IdType.INPUT);
        } else {
            environment.getSystemProperties().put(ID_TYPE_KEY, IdType.AUTO);
        }
    }

    private DbType getDbType(ConfigurableEnvironment environment) {
        String url = environment.getProperty("spring.datasource.url");
        if (url == null || url.isEmpty()) {
            String primary = environment.getProperty("spring.datasource.dynamic.primary");
            if (primary != null && !primary.isEmpty()) {
                url = environment.getProperty("spring.datasource.dynamic.datasource." + primary + ".url");
            }
        }
        if (url == null || url.isEmpty()) {
            return null;
        }
        return com.baomidou.mybatisplus.extension.toolkit.JdbcUtils.getDbType(url);
    }
}

