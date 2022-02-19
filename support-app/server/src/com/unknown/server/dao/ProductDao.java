package com.unknown.server.dao;

import com.unknown.server.entities.Product;

import java.util.List;

public interface ProductDao {

    List<String> loadAllProductTypes();

    List<Product> loadAllProducts();

    List<Product> loadProductsByType(String type);

    int loadIdByModel(String model);

}
