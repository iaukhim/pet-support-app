package com.unknown.server.dao;

import com.unknown.server.entities.OwnedProduct;

import java.util.List;

public interface OwnedProductDao {

    void saveProduct(OwnedProduct product);

    List<OwnedProduct> loadUsersProducts (String email);

    void deleteUserProduct (OwnedProduct product);

    boolean changeSerial (String oldValue, String newValue);

    boolean checkSerial (String serialNumber);

    OwnedProduct loadById(int id);

    String loadModelById(int id);
}
