package com.unknown.supportapp.client.common.service.product;

import com.unknown.supportapp.client.common.exception.CustomServerError;

import java.util.List;

public interface LoadAllProductTypesService {
    List<String> load() throws CustomServerError;
}
