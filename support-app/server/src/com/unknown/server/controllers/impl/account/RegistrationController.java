package com.unknown.server.controllers.impl.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.server.common.Response;
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


        Response okResponse = Response.getOkResponse();

        ObjectNode response = JsonNodeFactory.instance.objectNode();
        ObjectNode header = response.putObject("response-header");
        header.put("response-code", okResponse.getCode());
        ArrayNode body = response.putArray("response-body");
        ObjectNode bodyObject = JsonNodeFactory.instance.objectNode();
        bodyObject.put("response-message", okResponse.getMessage());
        bodyObject.put("confirmation-code", confirmationCode);
        body.add(bodyObject);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

