package com.unknown.supportapp.client.common.service.impl.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.service.account.RegistrationService;
import com.unknown.supportapp.client.common.net.ServerConnection;


public class RegistrationServiceImpl implements RegistrationService {
    @Override
    public String registration(String email, String password) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("registration", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode == 200){
            JsonNode responseBody = connection.getResponseBody();
            for (JsonNode responseObject : responseBody) {
                return responseObject.get("confirmation-code").asText();
            }
        }
        throw new RuntimeException("Server Error");
    }
}
