package com.unknown.supportapp.client.common.service.impl.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.product.LoadAllProductTypesService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadAllProductTypesImpl implements LoadAllProductTypesService {

    private ExceptionHandler exceptionHandler;

    @Override
    public List<String> load() throws CustomServerError {
        List<String> productTypes;

        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-product-types");
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode >= 500){
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }

        JsonNode responseBody = connection.getResponseBody();
        try {
            String[] strings = connection.getObjectMapper().readValue(responseBody.get("product-types").toString(), String[].class);
            productTypes = new ArrayList<>(Arrays.asList(strings));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
        return productTypes;
    }
}
