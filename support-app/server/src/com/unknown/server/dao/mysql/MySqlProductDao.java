package com.unknown.server.dao.mysql;

import com.unknown.server.dao.ProductDao;
import com.unknown.server.db.mysql.DbConnectionManager;
import com.unknown.server.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductDao implements ProductDao {

    @Override
    public int loadIdByModel(String model) {
        int id = -1;

        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadIdByModelSql = "SELECT id FROM pet_db.products WHERE model = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadIdByModelSql);
            preparedStatement.setString(1, model);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }
        try {
            while (resultSet.next()){
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return id;
    }

    @Override
    public List<Product> loadProductsByType(String type) {
        List<Product> products = new ArrayList<>();

        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadProductsByTypeSql = "SELECT * FROM pet_db.products WHERE `type` = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadProductsByTypeSql);
            preparedStatement.setString(1, type);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }
        try {
            while (resultSet.next()){
                String model = resultSet.getString("model");
                int id = resultSet.getInt("id");

                Product product = new Product();
                product.setId(id);
                product.setType(type);
                product.setModel(model);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return products;
    }

    @Override
    public List<Product> loadAllProducts() {
        List<Product> products = new ArrayList<>();

        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadAllProductsSql = "SELECT * FROM pet_db.products";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadAllProductsSql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }
        try {
            while (resultSet.next()){
                String type = resultSet.getString("type");
                String model = resultSet.getString("model");
                int id = resultSet.getInt("id");

                Product product = new Product(id, type, model);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return products;
    }

    @Override
    public List<String> loadAllProductTypes() {
        ArrayList<String> productTypes = new ArrayList<>();

        Connection connection = DbConnectionManager.getManager().getConnection();
        String loadAllProductTypesSql = "SELECT `type` FROM pet_db.product_types";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(loadAllProductTypesSql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in ProductDao", e);
        }
        try {
            while (resultSet.next()){
                String type = resultSet.getString("type");
                productTypes.add(type);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception in ProductDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return productTypes;
    }


}
