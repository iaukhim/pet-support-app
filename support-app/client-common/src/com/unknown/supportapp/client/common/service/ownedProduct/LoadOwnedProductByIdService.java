package com.unknown.supportapp.client.common.service.ownedProduct;

import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

public interface LoadOwnedProductByIdService {

    OwnedProductDto load(int id);
}
