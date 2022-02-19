package com.unknown.server.dao.mysql;


import com.unknown.server.dao.AccountDao;
import com.unknown.server.db.mysql.DbConnectionManager;
import com.unknown.server.entities.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MySqlAccountDao implements AccountDao {


    public MySqlAccountDao() {

    }

    @Override
    public boolean logIn(Account account) {
        boolean result = false;
        Connection connection = DbConnectionManager.getManager().getConnection();
        String logInSql = "SELECT `email`, `password` FROM pet_db.accounts WHERE `email` = ? AND `password` = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(logInSql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
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
    public List<Account> loadAll() {
        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadAllSql = "SELECT * FROM pet_db.accounts";
        List<Account> accounts = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(loadAllSql);
            preparedStatement.executeQuery();

            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                Account account = new Account();
                account.setEmail(email);
                account.setPassword(password);

                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return accounts;
    }

    @Override
    public Account loadByEmail(String email) {

        Account account = new Account();
        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadByEmailAccountSql = "SELECT * FROM pet_db.accounts WHERE `email` = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadByEmailAccountSql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String accountEmail = resultSet.getString("email");
                String accountPassword = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phoneNumber = resultSet.getString("phone_number");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int id = resultSet.getInt("id");

                account.setEmail(accountEmail);
                account.setPassword(accountPassword);
                account.setName(name);
                account.setSurname(surname);
                account.setPhoneNumber(phoneNumber);
                account.setId(id);
                if(dateOfBirth != null){
                    account.setDateOfBirth(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return account;
    }


    @Override
    public void delete(int id) {
        Connection connection = DbConnectionManager.getManager().getConnection();
        String deleteAccountSql = "DELETE FROM pet_db.accounts WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(deleteAccountSql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }
        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }

    @Override
    public void save(Account account) {
        Connection connection = DbConnectionManager.getManager().getConnection();
        String saveAccountSql = "INSERT INTO pet_db.accounts (`email`, `password`, `name`, `surname`, `phone_number`, `date_of_birth`) VALUES (?, ?, '', '', '', '2099-11-11')";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(saveAccountSql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }
        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }

    @Override
    public boolean checkAccountExistence(String email) {
        boolean result = false;
        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadByEmailAccountSql = "SELECT `email` FROM pet_db.accounts WHERE `email` = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadByEmailAccountSql);
            preparedStatement.setString(1, email);
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
    public void changePassword(Account account) {
        Connection connection = DbConnectionManager.getManager().getConnection();
        String changePasswordSql = "UPDATE pet_db.accounts SET `password` = ? WHERE `email`=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(changePasswordSql);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }
        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }

    @Override
    public int loadIdByEmail(String email) {
        int id  = -1;
        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadIdByEmailSql = "SELECT `id` FROM pet_db.accounts WHERE `email` = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadIdByEmailSql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        try {
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return id;

    }

    @Override
    public void update(Account account) {
        Connection connection = DbConnectionManager.getManager().getConnection();
        String changePasswordSql = "UPDATE pet_db.accounts SET `email` = ?, `password` = ?, `name` = ?, `surname` = ?, `phone_number` = ?, `date_of_birth` = STR_TO_DATE(?, '%Y-%m-%d') WHERE `id`= ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(changePasswordSql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getSurname());
            preparedStatement.setString(5, account.getPhoneNumber());
            preparedStatement.setString(6, account.getDateOfBirth().toString());
            preparedStatement.setInt(7, account.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }
        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }
}
