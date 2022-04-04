package com.unknown.supportapp.client.common.service.impl.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.manager.ManagerLoginService;
import com.unknown.supportapp.common.dto.manager.ManagerDto;

public class ManagerLoginServiceImpl implements ManagerLoginService {

    private ExceptionHandler exceptionHandler;

    @Override
    public boolean login(ManagerDto managerDto) throws CustomServerError {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("manager-log-in", "account", managerDto);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode >= 500) {
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }

        if (responseCode == 200) {
            return true;
        }
        return false;
    }
}
