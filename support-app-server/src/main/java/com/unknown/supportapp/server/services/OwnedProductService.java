package com.unknown.supportapp.server.services;

import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

import java.util.List;

public interface OwnedProductService {

    List<OwnedProductDto> loadUsersProducts(String email);

    void deleteUserProduct(OwnedProductDto productDto);

    boolean changeSerial(String oldValue, String newValue);

    void saveProduct(OwnedProductDto productDto);

    boolean checkSerial(String serialNumber);

    OwnedProductDto loadById(int id);

    String loadModelById(int id);
}
