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

public class CheckAccountExistenceController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        boolean result = false;

        String email = requestBody.get("email").asText();

        AccountService accountService = ServiceFactory.getFactory().getAccountService();
        result = accountService.checkExistence(email);

        Response okResponse = Response.getOkResponse();
        Response badRequestResponse = Response.getBadRequestResponse();
        ObjectNode response;

        if (result) {
            response = JsonNodeFactory.instance.objectNode();
            ObjectNode header = response.putObject("response-header");
            header.put("response-code", okResponse.getCode());
            ArrayNode body = response.putArray("response-body");
            ObjectNode bodyObject = JsonNodeFactory.instance.objectNode();
            bodyObject.put("response-message", okResponse.getMessage());
            body.add(bodyObject);
        } else {
            response = JsonNodeFactory.instance.objectNode();
            ObjectNode header = response.putObject("response-header");
            header.put("response-code", badRequestResponse.getCode());
            ArrayNode body = response.putArray("response-body");
            ObjectNode bodyObject = JsonNodeFactory.instance.objectNode();
            bodyObject.put("response-message", badRequestResponse.getMessage());
            body.add(bodyObject);
        }

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
