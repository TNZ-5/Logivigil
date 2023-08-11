package com.application.services;
import com.application.constants.DatabaseConst;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl(DatabaseConst.Constants.URL);
        config.setUsername(DatabaseConst.Constants.USERNAME);
        config.setPassword( DatabaseConst.Constants.PASSWORD);
        try {
            Class.forName(DatabaseConst.Constants.CLASS_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ds = new HikariDataSource( config );
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}