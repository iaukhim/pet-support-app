package com.unknown.supportapp.client.common.service.impl.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.account.CheckAccountExistenceService;


public class CheckAccountExistenceServiceImpl implements CheckAccountExistenceService {
    @Override
    public boolean check(String email) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("check-existence", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode == 200) {
            return true;
        }
        return false;
    }
}
