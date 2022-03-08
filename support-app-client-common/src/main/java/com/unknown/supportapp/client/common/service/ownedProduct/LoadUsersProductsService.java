package com.unknown.supportapp.client.common.service.ownedProduct;

import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

import java.util.List;

public interface LoadUsersProductsService {

    List<OwnedProductDto> load (String email);
}
