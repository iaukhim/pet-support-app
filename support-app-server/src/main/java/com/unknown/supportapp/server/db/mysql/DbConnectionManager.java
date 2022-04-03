package com.unknown.supportapp.server.db.mysql;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbConnectionManager {


    private static final DbConnectionManager manager = new DbConnectionManager();

    private Properties properties;

    private BasicDataSource pool;

    private DbConnectionManager() {
        properties = new Properties();

        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("db-settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Can not load server-settings.properties! Server will not start!", e);
        }

        try {
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can not load DB Driver! Server will not start!", e);
        }

        setPool();
    }

    public static DbConnectionManager getManager() {
        return manager;
    }

    public BasicDataSource getPool() {
        return pool;
    }

    private void  setPool(){
        BasicDataSource pool = new BasicDataSource();

        pool.setDriverClassName(properties.getProperty("db.driver"));
        pool.setUrl(properties.getProperty("jdbc.url"));
        pool.setUsername(properties.getProperty("user"));
        pool.setPassword(properties.getProperty("password"));

        pool.setMaxTotal(100);
        pool.setMaxIdle(10);

        this.pool = pool;
    }


    public Connection getConnection() {
        try {
            Connection connection = pool.getConnection();
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Errors occurred during DB access!", e);
        }
    }

    public void closeDbResources(Connection connection, Statement statement) {
        closeDbResources(connection, statement, null);
    }

    public void closeDbResources(Connection connection, Statement statement, ResultSet resultSet) {
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: Connection has not been closed!");
            }
        }
    }

    private void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error: Statement has not been closed!");
            }
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error: ResultSet has not been closed!");
            }
        }
    }
}
