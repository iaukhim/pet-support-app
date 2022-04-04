package com.unknown.supportapp.client.common.service.ownedProduct;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface CheckSerialService {

    boolean check(String serialNumber) throws CustomServerError;
}
