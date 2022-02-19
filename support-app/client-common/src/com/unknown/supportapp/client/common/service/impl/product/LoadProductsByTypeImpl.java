package com.unknown.supportapp.client.common.service.impl.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.product.LoadProductsByTypeService;
import com.unknown.supportapp.common.dto.product.ProductDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadProductsByTypeImpl implements LoadProductsByTypeService {
    @Override
    public List<ProductDto> load(String type) {
        List<ProductDto> products;

        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-products-by-type", "type", type);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode != 200) {
            throw new RuntimeException("Server error");
        }

        JsonNode responseBody = connection.getResponseBody();
        try {
            ProductDto[] array = connection.getObjectMapper().readValue(responseBody.get("products").toString(), ProductDto[].class);
            products = new ArrayList<>(Arrays.asList(array));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
        return products;
    }
}

