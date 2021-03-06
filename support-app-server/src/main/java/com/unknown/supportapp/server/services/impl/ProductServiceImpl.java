package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.dao.ProductDao;
import com.unknown.supportapp.server.services.ProductService;
import com.unknown.supportapp.common.dto.product.ProductDto;
import com.unknown.supportapp.server.entities.Product;
import com.unknown.supportapp.server.entities.converters.ProductConverter;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    private ProductConverter productConverter;

    public ProductServiceImpl() {
    }

    public ProductServiceImpl(ProductDao productDao, ProductConverter productConverter) {
        this.productDao = productDao;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDto> loadProductsByType(String type) {
        List<Product> products = productDao.loadProductsByType(type);
        return productConverter.convertToDtoList(products);
    }

    @Override
    public List<ProductDto> loadAllProducts() {
        List<Product> products = productDao.loadAllProducts();
        return productConverter.convertToDtoList(products);
    }

    @Override
    public List<String> loadAllProductTypes() {
        return productDao.loadAllProductTypes();
    }

    @Override
    public int loadIdByModel(String model) {
        int id = productDao.loadIdByModel(model);
        return id;
    }
}
