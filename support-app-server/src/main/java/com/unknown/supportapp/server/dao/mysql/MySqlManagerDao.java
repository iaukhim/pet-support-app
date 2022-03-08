package com.unknown.supportapp.server.dao.mysql;

import com.unknown.supportapp.server.dao.ManagerDao;
import com.unknown.supportapp.server.db.mysql.DbConnectionManager;
import com.unknown.supportapp.server.entities.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlManagerDao implements ManagerDao {

    public MySqlManagerDao() {
    }

    private final  String LOG_IN = "SELECT `email`, `password` FROM pet_db.managers_accounts WHERE `email` = ? AND `password` = ?";
    private final String LOAD_ID_BY_EMAIL = "SELECT `id` FROM pet_db.managers_accounts WHERE `email` = ?";

    @Override
    public boolean login(Manager manager) {
        boolean result = false;
        Connection connection = DbConnectionManager.getManager().getConnection();

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(LOG_IN);
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

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(LOAD_ID_BY_EMAIL);
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
