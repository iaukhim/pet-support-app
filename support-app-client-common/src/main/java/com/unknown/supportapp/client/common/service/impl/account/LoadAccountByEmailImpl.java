package com.unknown.supportapp.client.common.service.impl.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.account.LoadAccountByEmail;
import com.unknown.supportapp.common.dto.acccount.AccountDto;

public class LoadAccountByEmailImpl implements LoadAccountByEmail {
    @Override
    public AccountDto load(String email) {


        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-account-by-email", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        if (responseCode != 200){
            connection.close();
            throw new RuntimeException("Server-error");
        }

        JsonNode responseBody = connection.getResponseBody();
        AccountDto accountDto;

        try {
            accountDto = connection.getObjectMapper().readValue(responseBody.get("account").toString(), AccountDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        connection.close();
        return accountDto;
    }
}
