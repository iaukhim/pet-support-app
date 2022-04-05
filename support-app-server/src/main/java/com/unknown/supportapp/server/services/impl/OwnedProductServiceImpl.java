package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.dao.OwnedProductDao;
import com.unknown.supportapp.server.entities.OwnedProduct;
import com.unknown.supportapp.server.entities.converters.OwnedProductConverter;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.server.services.OwnedProductService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class OwnedProductServiceImpl implements OwnedProductService {

    private OwnedProductDao ownedProductDao;

    private OwnedProductConverter ownedProductConverter;

    public OwnedProductServiceImpl() {
    }

    public OwnedProductServiceImpl(OwnedProductDao ownedProductDao, OwnedProductConverter ownedProductConverter) {
        this.ownedProductDao = ownedProductDao;
        this.ownedProductConverter = ownedProductConverter;
    }

    @Override
    public List<OwnedProductDto> loadUsersProducts(String email) {
        List<OwnedProduct> products = ownedProductDao.loadUsersProducts(email);
        List<OwnedProductDto> ownedProductDtos = ownedProductConverter.convertToDtoList(products);
        return ownedProductDtos;
    }
    @Override
    public void saveProduct(OwnedProductDto productDto) {
        OwnedProduct ownedProduct = ownedProductConverter.convertToEntity(productDto);
        ownedProductDao.saveProduct(ownedProduct);
    }
    @Override
    public boolean changeSerial(String oldValue, String newValue) {
        boolean result = ownedProductDao.changeSerial(oldValue, newValue);
        return result;
    }

    @Override
    public void deleteUserProduct(OwnedProductDto productDto) {
        OwnedProduct entity = ownedProductConverter.convertToEntity(productDto);
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
        OwnedProductDto productDto = ownedProductConverter.convertToDto(entity);
        return productDto;
    }

    @Override
    public String loadModelById(int id) {
        String model = ownedProductDao.loadModelById(id);
        return model;
    }
}
