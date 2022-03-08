package com.unknown.supportapp.client.common.service.impl.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.account.LogInService;
import com.unknown.supportapp.common.dto.acccount.AccountDto;


public class LogInServiceImpl implements LogInService {

    @Override
    public boolean LogIn(String email, String password) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("log-in", "account", new AccountDto(email, password));
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode == 200){
            return true;
        }
        if (responseCode == 400){
            return false;
        }
        throw new RuntimeException("Server error");
    }
}
