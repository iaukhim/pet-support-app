package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.services.ProductService;
import com.unknown.supportapp.common.dto.product.ProductDto;
import com.unknown.supportapp.server.dao.factory.DaoFactory;
import com.unknown.supportapp.server.entities.Product;
import com.unknown.supportapp.server.entities.converters.ProductConverter;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductDto> loadProductsByType(String type) {
        List<Product> products = DaoFactory.getFactory().getProductDao().loadProductsByType(type);
        return new ProductConverter().convertToDtoList(products);
    }


    @Override
    public List<ProductDto> loadAllProducts() {
        List<Product> products = DaoFactory.getFactory().getProductDao().loadAllProducts();
        return new ProductConverter().convertToDtoList(products);
    }

    @Override
    public List<String> loadAllProductTypes() {
        return DaoFactory.getFactory().getProductDao().loadAllProductTypes();
    }



    @Override
    public int loadIdByModel(String model) {
        int id = DaoFactory.getFactory().getProductDao().loadIdByModel(model);
        return id;
    }
}
