package com.insurance.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String conn = PropertyUtil.getPropertyString("db.properties");
            connection = DriverManager.getConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
