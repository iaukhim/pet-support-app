package com.unknown.supportapp.client.common.service.impl.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.account.UpdateAccountService;
import com.unknown.supportapp.common.dto.acccount.AccountDto;

public class UpdateAccountImpl implements UpdateAccountService {

    private ExceptionHandler exceptionHandler;

    @Override
    public void update(AccountDto account) throws CustomServerError {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("update-account", "account", account);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode >= 500){
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }
    }
}
