package com.unknown.supportapp.server.services;

import com.unknown.supportapp.common.dto.product.ProductDto;


import java.util.List;

public interface ProductService {

    List<String> loadAllProductTypes();

    List<ProductDto> loadAllProducts();

    List<ProductDto> loadProductsByType(String type);

    int loadIdByModel(String model);
}
