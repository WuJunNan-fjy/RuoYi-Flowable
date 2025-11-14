package com.ruoyi.starters.mybatisplus.core.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.ruoyi.common.utils.spring.SpringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {
    public static DbType getDbType(String url) {
        return com.baomidou.mybatisplus.extension.toolkit.JdbcUtils.getDbType(url);
    }

    public static DbType getDbType() {
        DataSource dataSource = SpringUtils.getBean(DataSource.class);
        try (Connection conn = dataSource.getConnection()) {
            String productName = conn.getMetaData().getDatabaseProductName();
            return com.ruoyi.starters.mybatisplus.core.enums.DbTypeEnum.find(productName);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static boolean isSQLServer(DbType dbType) {
        return dbType == DbType.SQL_SERVER || dbType == DbType.SQL_SERVER2005;
    }
}

