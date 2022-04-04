package com.unknown.supportapp.client.common.service.impl.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.account.ConfirmationCodeService;


public class ConfirmationCodeServiceImpl implements ConfirmationCodeService {

    private ExceptionHandler exceptionHandler;

    @Override
    public String send(String email) throws CustomServerError {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("confirmation-code", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode >= 500){
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }


        JsonNode responseBody = connection.getResponseBody();
        String confirmationCode = responseBody.get("confirmation-code").asText();

        return confirmationCode;
    }
}
