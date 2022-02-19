package com.unknown.supportapp.client.common.service.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ownedProduct.CheckSerialService;

public class CheckSerialImpl implements CheckSerialService {
    @Override
    public boolean check(String serialNumber) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("check-serial", "serialNumber", serialNumber);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();

        if(responseCode != 200){
            throw new RuntimeException("Server error");
        }

        boolean result = connection.getResponseBody().get("result").asBoolean();
        connection.close();
        return result;
    }
}
