package com.unknown.supportapp.client.common.service.ownedProduct;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;


public interface SaveUserProductService {
    void save(OwnedProductDto productDto) throws CustomServerError;
}
