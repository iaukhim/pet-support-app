package com.unknown.server.dao.mysql;

import com.unknown.server.dao.ManagerDao;
import com.unknown.server.db.mysql.DbConnectionManager;
import com.unknown.server.entities.Account;
import com.unknown.server.entities.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MySqlManagerDao implements ManagerDao {
    @Override
    public boolean login(Manager manager) {
        boolean result = false;
        Connection connection = DbConnectionManager.getManager().getConnection();
        String logInSql = "SELECT `email`, `password` FROM pet_db.managers_accounts WHERE `email` = ? AND `password` = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(logInSql);
            preparedStatement.setString(1, manager.getEmail());
            preparedStatement.setString(2, manager.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }
        try {
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return result;
    }

    @Override
    public int loadIdByEmail(String email) {
        int id = -1;
        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadIdByEmailSql = "SELECT `id` FROM pet_db.managers_accounts WHERE `email` = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadIdByEmailSql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");

            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return id;
    }
}
