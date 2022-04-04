package com.unknown.supportapp.client.common.service.impl.ownedProduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ownedProduct.LoadUsersProductsService;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;

import java.util.Arrays;
import java.util.List;

public class LoadUsersProductsImpl implements LoadUsersProductsService {

    private ExceptionHandler exceptionHandler;

    @Override
    public List<OwnedProductDto> load(String email) throws CustomServerError {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-users-products", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode == 200) {
            JsonNode responseBody = connection.getResponseBody();
            ObjectMapper objectMapper = connection.getObjectMapper();

            try {
                OwnedProductDto[] array = objectMapper.readValue(responseBody.get("owned-products").toString(), OwnedProductDto[].class);
                return Arrays.asList(array);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
            return null;
        }
    }
}
