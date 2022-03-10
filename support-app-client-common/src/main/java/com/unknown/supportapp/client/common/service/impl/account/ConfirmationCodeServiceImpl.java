package com.unknown.supportapp.client.common.service.impl.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.account.ConfirmationCodeService;


public class ConfirmationCodeServiceImpl implements ConfirmationCodeService {
    @Override
    public String send(String email) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("confirmation-code", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        if (responseCode != 200){
            connection.close();
            throw new RuntimeException("Server-error");
        }


        JsonNode responseBody = connection.getResponseBody();
        String confirmationCode = responseBody.get("confirmation-code").asText();
        connection.close();
        return confirmationCode;
    }
}
