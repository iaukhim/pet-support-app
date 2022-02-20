package com.unknown.server.controllers.impl.account;

import com.fasterxml.jackson.databind.JsonNode;

import com.unknown.server.common.Response;
import com.unknown.server.common.ResponseFactory;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.AccountService;
import com.unknown.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class RegistrationController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String email = requestBody.get("email").asText();

        AccountService accountService = ServiceFactory.getFactory().getAccountService();
        String confirmationCode = accountService.registration(email);

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "confirmation-code", confirmationCode);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

