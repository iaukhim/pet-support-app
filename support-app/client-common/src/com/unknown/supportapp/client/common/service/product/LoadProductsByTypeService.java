package com.unknown.supportapp.client.common.service.product;


import com.unknown.supportapp.common.dto.product.ProductDto;

import java.util.List;

public interface LoadProductsByTypeService {
    List<ProductDto> load (String type);
}
