package com.unknown.supportapp.client.common.service.ownedProduct;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface OwnedProductLoadModelByIdService {

    String load(int id) throws CustomServerError;
}
