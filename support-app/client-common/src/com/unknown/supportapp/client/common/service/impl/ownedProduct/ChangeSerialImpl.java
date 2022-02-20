package com.unknown.supportapp.client.common.service.impl.ownedProduct;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ownedProduct.ChangeSerialService;

public class ChangeSerialImpl implements ChangeSerialService {
    @Override
    public boolean change(String oldValue, String newValue) {
        ServerConnection connection = new ServerConnection();

        ObjectNode body = JsonNodeFactory.instance.objectNode();
        body.put("oldValue", oldValue);
        body.put("newValue", newValue);
        JsonNode request = RequestFactory.getFactory().formRequest("change-serial", body);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();


        if(responseCode == 200){
            connection.close();
            return true;
        }
        if(responseCode == 400){
            connection.close();
            return false;
        }
        else {
            throw new RuntimeException("Server error");
        }
    }
}
