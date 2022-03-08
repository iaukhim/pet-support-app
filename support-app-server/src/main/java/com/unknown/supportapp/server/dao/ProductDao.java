package com.unknown.supportapp.server.dao;

import com.unknown.supportapp.server.entities.Product;

import java.util.List;

public interface ProductDao {

    List<String> loadAllProductTypes();

    List<Product> loadAllProducts();

    List<Product> loadProductsByType(String type);

    int loadIdByModel(String model);

}
