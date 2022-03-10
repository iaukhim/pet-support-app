package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.dao.OwnedProductDao;
import com.unknown.supportapp.server.dao.factory.DaoFactory;
import com.unknown.supportapp.server.entities.OwnedProduct;
import com.unknown.supportapp.server.entities.converters.OwnedProductConverter;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.server.services.OwnedProductService;

import java.util.List;

public class OwnedProductServiceImpl implements OwnedProductService {

    private OwnedProductDao ownedProductDao;

    public OwnedProductServiceImpl() {
    }

    public OwnedProductServiceImpl(OwnedProductDao ownedProductDao) {
        this.ownedProductDao = ownedProductDao;
    }

    @Override
    public List<OwnedProductDto> loadUsersProducts(String email) {
        List<OwnedProduct> products = ownedProductDao.loadUsersProducts(email);
        List<OwnedProductDto> ownedProductDtos = new OwnedProductConverter().convertToDtoList(products);
        return ownedProductDtos;
    }
    @Override
    public void saveProduct(OwnedProductDto productDto) {
        OwnedProduct ownedProduct = new OwnedProductConverter().convertToEntity(productDto);
        ownedProductDao.saveProduct(ownedProduct);
    }
    @Override
    public boolean changeSerial(String oldValue, String newValue) {
        boolean result = ownedProductDao.changeSerial(oldValue, newValue);
        return result;
    }

    @Override
    public void deleteUserProduct(OwnedProductDto productDto) {
        OwnedProduct entity = new OwnedProductConverter().convertToEntity(productDto);
        ownedProductDao.deleteUserProduct(entity);
    }

    @Override
    public boolean checkSerial(String serialNumber) {
        if (serialNumber.trim().length() == 0){
            return false;
        }
        boolean result = ownedProductDao.checkSerial(serialNumber);
        return result;
    }

    @Override
    public OwnedProductDto loadById(int id) {
        OwnedProduct entity = ownedProductDao.loadById(id);
        OwnedProductDto productDto = new OwnedProductConverter().convertToDto(entity);
        return productDto;
    }

    @Override
    public String loadModelById(int id) {
        String model = ownedProductDao.loadModelById(id);
        return model;
    }
}
