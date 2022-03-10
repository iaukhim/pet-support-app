package com.unknown.supportapp.server.controllers.impl.account;

import com.fasterxml.jackson.databind.JsonNode;

import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;

import com.unknown.supportapp.server.services.AccountService;

import java.io.BufferedWriter;
import java.io.IOException;

public class ConfirmationCodeController implements Controller {

    private AccountService service;

    public ConfirmationCodeController() {
    }

    public ConfirmationCodeController(AccountService service) {
        this.service = service;
    }

    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String email = requestBody.get("email").asText();
        String code = service.confirmationCode(email);

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "confirmation-code", code);

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
