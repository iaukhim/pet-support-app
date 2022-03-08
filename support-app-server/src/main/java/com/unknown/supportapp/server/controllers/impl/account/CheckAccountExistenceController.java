package com.unknown.supportapp.server.controllers.impl.account;

import com.fasterxml.jackson.databind.JsonNode;

import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.AccountService;
import com.unknown.supportapp.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class CheckAccountExistenceController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        boolean result = false;

        String email = requestBody.get("email").asText();

        AccountService accountService = ServiceFactory.getFactory().getAccountService();
        result = accountService.checkExistence(email);

        JsonNode response;

        if (result) {
            response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());

        } else {
            response = ResponseFactory.getFactory().formResponse(Response.getBadRequestResponse());
        }

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
