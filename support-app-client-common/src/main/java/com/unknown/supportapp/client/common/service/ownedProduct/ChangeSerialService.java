package com.unknown.supportapp.client.common.service.ownedProduct;


import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface ChangeSerialService {

    boolean change(String oldValue, String newValue) throws CustomServerError;
}
