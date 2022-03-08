package com.unknown.supportapp.client.common.service.impl.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.manager.ManagerLoadIdByEmailService;

public class ManagerLoadIdByEmailImpl implements ManagerLoadIdByEmailService {
    @Override
    public int load(String email) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("manager-load-id-by-email", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        if (responseCode != 200){
            connection.close();
            throw new RuntimeException("Server-error");
        }

        int id = -1;

        JsonNode responseBody = connection.getResponseBody();
        id = responseBody.get("id").asInt();

        connection.close();
        return id;
    }
}
