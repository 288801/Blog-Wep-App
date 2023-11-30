package ru.vsu.rogachev.blog.db;

import java.sql.*;
import java.util.Properties;

public class ConnectionManager {
    private static ConnectionManager instance;
    private static final String DB_URL = "jdbc:mysql://localhost:3307/blog_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "1111";

    // JDBC variables for opening and managing connection
    private static Connection connection;
    private static Statement statement;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            instance = new ConnectionManager();
            instance.connect();
        }
        return instance;
    }

    public Connection reconnect() {
        try {
            connection.close();
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = createStatement(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void connect() {
        try {
            Properties properties = new Properties();
            properties.setProperty("autoReconnect", "true");
            properties.setProperty("connectTimeout", "50000");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public ResultSet executeSelect(String query) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return resultSet;
    }

    public int executeUpdate(String query) {
        int rows = 0;
        try {
            rows = statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rows;
    }
}