package com.company.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContext {

    private static final String DB_NAME = "flowers.accdb";
    private static final String DB_CONNECTION_STRING = "jdbc:ucanaccess://" + DB_NAME;

    public DbContext() throws ClassNotFoundException {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION_STRING, "", "");
    }
}
