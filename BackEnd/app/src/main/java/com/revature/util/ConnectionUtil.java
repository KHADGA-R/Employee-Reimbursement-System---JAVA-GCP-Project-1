package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://34.69.253.208:5432/postgres";
        String user = "postgres";
        String pass = "postgres";
        return DriverManager.getConnection(url, user, pass);
    }
}
