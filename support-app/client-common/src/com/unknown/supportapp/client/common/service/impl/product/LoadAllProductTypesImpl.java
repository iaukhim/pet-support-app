package com.unknown.supportapp.client.common.service.impl.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.product.LoadAllProductTypesService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadAllProductTypesImpl implements LoadAllProductTypesService {
    @Override
    public List<String> load() {
        List<String> productTypes;

        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-product-types");
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if(responseCode != 200){
            throw new RuntimeException("Server error");
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
