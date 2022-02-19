package com.unknown.supportapp.client.common.service.product;


import com.unknown.supportapp.common.dto.product.ProductDto;

import java.util.List;

public interface LoadAllProductsService {
    List<ProductDto> load();
}
