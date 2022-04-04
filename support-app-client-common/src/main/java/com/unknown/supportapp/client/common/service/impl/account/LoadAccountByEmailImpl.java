package com.unknown.supportapp.client.common.service.impl.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.account.LoadAccountByEmail;
import com.unknown.supportapp.common.dto.acccount.AccountDto;

public class LoadAccountByEmailImpl implements LoadAccountByEmail {

    private ExceptionHandler exceptionHandler;

    @Override
    public AccountDto load(String email) throws CustomServerError {


        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-account-by-email", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode >= 500){
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }

        JsonNode responseBody = connection.getResponseBody();
        AccountDto accountDto;

        try {
            accountDto = connection.getObjectMapper().readValue(responseBody.get("account").toString(), AccountDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return accountDto;
    }
}
