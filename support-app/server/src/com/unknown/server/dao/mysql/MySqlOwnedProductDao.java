package com.unknown.server.dao.mysql;

import com.unknown.server.dao.OwnedProductDao;
import com.unknown.server.db.mysql.DbConnectionManager;
import com.unknown.server.entities.OwnedProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlOwnedProductDao implements OwnedProductDao {

    @Override
    public List<OwnedProduct> loadUsersProducts(String email) {
        List<OwnedProduct> products = new ArrayList<>();

        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadUsersProductsSql = "SELECT * from pet_db.owned_products WHERE owner_id IN ( SELECT id FROM pet_db.accounts WHERE `email` = ?)";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadUsersProductsSql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }
        try {
            while (resultSet.next()){
                String type = resultSet.getString("type");
                String model = resultSet.getString("model");
                String serialNumber = resultSet.getString("serial_number");
                int ownerId = resultSet.getInt("owner_id");
                int id = resultSet.getInt("id");

                OwnedProduct product = new OwnedProduct(id, ownerId, type, model, serialNumber);

                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return products;
    }

    @Override
    public void saveProduct(OwnedProduct product) {
        Connection connection = DbConnectionManager.getManager().getConnection();
        String saveProductSql = "INSERT INTO pet_db.owned_products (owner_id, `type`, model,  serial_number) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(saveProductSql);
            preparedStatement.setInt(1, product.getOwnerId());
            preparedStatement.setString(2, product.getType());
            preparedStatement.setString(3, product.getModel());
            preparedStatement.setString(4, product.getSerialNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }
    @Override
    public boolean changeSerial(String oldValue, String newValue) {
        boolean result = false;

        Connection connection = DbConnectionManager.getManager().getConnection();
        String checkSerialSql = "SELECT serial_number FROM pet_db.owned_products WHERE serial_number = ?";
        String changeSerialSql = "UPDATE pet_db.owned_products SET serial_number = ? WHERE serial_number = ?";
        PreparedStatement preparedStatement;


        try {
            preparedStatement = connection.prepareStatement(checkSerialSql);
            preparedStatement.setString(1, newValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String serialNumber = resultSet.getString("serial_number");
                if(!serialNumber.equals(oldValue)){
                    return false;
                }
            }
            preparedStatement = connection.prepareStatement(changeSerialSql);
            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, oldValue);
            preparedStatement.execute();

            result = true;
        }
        catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
        return result;
    }

    @Override
    public void deleteUserProduct(OwnedProduct product) {
        Connection connection = DbConnectionManager.getManager().getConnection();
        String deleteUserProduct = "DELETE FROM pet_db.owned_products WHERE owner_id = ? AND serial_number = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(deleteUserProduct);
            preparedStatement.setInt(1, product.getOwnerId());
            preparedStatement.setString(2, product.getSerialNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }

    @Override
    public boolean checkSerial(String serialNumber) {
        boolean result = true;

        Connection connection = DbConnectionManager.getManager().getConnection();
        String checkSerialSql = "SELECT * from pet_db.owned_products WHERE serial_number = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(checkSerialSql);
            preparedStatement.setString(1, serialNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);

        return result;
    }

    @Override
    public OwnedProduct loadById(int id) {
        OwnedProduct product = null;

        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadByIdSql = "SELECT * from pet_db.owned_products WHERE id = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadByIdSql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }
        try {
            while (resultSet.next()){
                String type = resultSet.getString("type");
                String model = resultSet.getString("model");
                String serialNumber = resultSet.getString("serial_number");
                int ownerId = resultSet.getInt("owner_id");

                product = new OwnedProduct(id, ownerId, type, model, serialNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return product;
    }

    @Override
    public String loadModelById(int id) {
        String model = null;

        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadModelByIdSql = "SELECT `model` from pet_db.owned_products WHERE id = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadModelByIdSql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }
        try {
            while (resultSet.next()){
                model = resultSet.getString("model");
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception in ProductDao", e);
        }
        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return model;
    }
}
