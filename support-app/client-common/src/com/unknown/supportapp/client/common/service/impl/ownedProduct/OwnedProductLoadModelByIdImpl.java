package com.unknown.supportapp.client.common.service.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ownedProduct.OwnedProductLoadModelByIdService;

public class OwnedProductLoadModelByIdImpl implements OwnedProductLoadModelByIdService {
    @Override
    public String load(int id) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("owned-product-load-model-by-id", "id", id);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();

        if(responseCode != 200){
            throw new RuntimeException("Server error");
        }

        String model = connection.getResponseBody().get("model").asText();
        connection.close();
        return model;
    }
}
