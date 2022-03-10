package com.unknown.supportapp.server.controllers.impl.account;

import com.fasterxml.jackson.databind.JsonNode;

import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.AccountService;
import com.unknown.supportapp.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class RegistrationController implements Controller {

    private AccountService service;

    public RegistrationController() {
    }

    public RegistrationController(AccountService service) {
        this.service = service;
    }

    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String email = requestBody.get("email").asText();

        String confirmationCode = service.registration(email);

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "confirmation-code", confirmationCode);

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

