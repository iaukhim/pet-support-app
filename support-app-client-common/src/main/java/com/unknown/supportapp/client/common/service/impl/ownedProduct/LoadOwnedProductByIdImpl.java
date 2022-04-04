package com.unknown.supportapp.client.common.service.impl.ownedProduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ownedProduct.LoadOwnedProductByIdService;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

public class LoadOwnedProductByIdImpl implements LoadOwnedProductByIdService {

    private ExceptionHandler exceptionHandler;

    @Override
    public OwnedProductDto load(int id) throws CustomServerError {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-owned-product-by-id", "id", id);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode >= 500){
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }

        if (responseCode == 200) {
            JsonNode responseBody = connection.getResponseBody();
            ObjectMapper objectMapper = connection.getObjectMapper();

            try {
                OwnedProductDto productDto = objectMapper.readValue(responseBody.get("product").toString(), OwnedProductDto.class);
                return productDto;

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            return new OwnedProductDto();
        }
    }

}
