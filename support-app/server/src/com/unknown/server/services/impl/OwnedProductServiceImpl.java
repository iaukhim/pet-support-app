package com.unknown.server.services.impl;

import com.unknown.server.dao.factory.DaoFactory;
import com.unknown.server.entities.OwnedProduct;
import com.unknown.server.entities.converters.OwnedProductConverter;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;
import com.unknown.server.services.OwnedProductService;

import java.util.List;

public class OwnedProductServiceImpl implements OwnedProductService {

    @Override
    public List<OwnedProductDto> loadUsersProducts(String email) {
        List<OwnedProduct> products = DaoFactory.getFactory().getOwnedProductDao().loadUsersProducts(email);
        List<OwnedProductDto> ownedProductDtos = new OwnedProductConverter().convertToDtoList(products);
        return ownedProductDtos;
    }
    @Override
    public void saveProduct(OwnedProductDto productDto) {
        OwnedProduct ownedProduct = new OwnedProductConverter().convertToEntity(productDto);

        DaoFactory.getFactory().getOwnedProductDao().saveProduct(ownedProduct);
    }
    @Override
    public boolean changeSerial(String oldValue, String newValue) {
        boolean result = DaoFactory.getFactory().getOwnedProductDao().changeSerial(oldValue, newValue);
        return result;
    }

    @Override
    public void deleteUserProduct(OwnedProductDto productDto) {
        OwnedProduct entity = new OwnedProductConverter().convertToEntity(productDto);

        DaoFactory.getFactory().getOwnedProductDao().deleteUserProduct(entity);

    }

    @Override
    public boolean checkSerial(String serialNumber) {
        if (serialNumber.trim().length() == 0){
            return false;
        }
        boolean result = DaoFactory.getFactory().getOwnedProductDao().checkSerial(serialNumber);

        return result;
    }

    @Override
    public OwnedProductDto loadById(int id) {
        OwnedProduct entity = DaoFactory.getFactory().getOwnedProductDao().loadById(id);
        OwnedProductDto productDto = new OwnedProductConverter().convertToDto(entity);
        return productDto;
    }

    @Override
    public String loadModelById(int id) {
        String model = DaoFactory.getFactory().getOwnedProductDao().loadModelById(id);
        return model;
    }
}
