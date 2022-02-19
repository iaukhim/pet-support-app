package com.unknown.supportapp.client.common.service.impl.ownedProduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ownedProduct.LoadOwnedProductByIdService;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

public class LoadOwnedProductByIdImpl implements LoadOwnedProductByIdService {
    @Override
    public OwnedProductDto load(int id) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-owned-product-by-id", "id", id);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode == 200) {
            JsonNode responseBody = connection.getResponseBody();
            ObjectMapper objectMapper = connection.getObjectMapper();

            try {
                OwnedProductDto productDto = objectMapper.readValue(responseBody.get("product").toString(), OwnedProductDto.class);
                return productDto;

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            connection.close();
            throw new RuntimeException("Server error");
        }
    }
}
