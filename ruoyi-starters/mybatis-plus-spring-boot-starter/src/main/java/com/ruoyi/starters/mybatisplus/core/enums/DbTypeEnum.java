package com.ruoyi.starters.mybatisplus.core.enums;

import com.baomidou.mybatisplus.annotation.DbType;

public enum DbTypeEnum {
    ;

    public static DbType find(String databaseProductName) {
        if (databaseProductName == null) {
            return null;
        }
        switch (databaseProductName) {
            case "MySQL":
                return DbType.MYSQL;
            case "PostgreSQL":
                return DbType.POSTGRE_SQL;
            case "Microsoft SQL Server":
                return DbType.SQL_SERVER;
            case "Microsoft SQL Server 2005":
                return DbType.SQL_SERVER2005;
            case "DM DBMS":
                return DbType.DM;
            case "KingbaseES":
                return DbType.KINGBASE_ES;
            case "Oracle":
                return DbType.ORACLE;
            case "H2":
                return DbType.H2;
            default:
                return DbType.MYSQL;
        }
    }
}

