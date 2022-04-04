package com.unknown.supportapp.client.common.service.impl.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.account.RegistrationService;


public class RegistrationServiceImpl implements RegistrationService {

    private ExceptionHandler exceptionHandler;

    @Override
    public String registration(String email, String password) throws CustomServerError {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("registration", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode >= 500) {
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }


        JsonNode responseBody = connection.getResponseBody();
        return responseBody.get("confirmation-code").asText();
    }
}
