package com.unknown.server.controllers.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.server.common.Response;
import com.unknown.server.controllers.Controller;

import java.io.BufferedWriter;
import java.io.IOException;

public class UnknownRequestController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {

        Response notFoundResponse = Response.getNotFoundResponse();

        ObjectNode response = JsonNodeFactory.instance.objectNode();

        ObjectNode header = response.putObject("response-header");
        header.put("response-code", notFoundResponse.getCode());
        ObjectNode body = response.putObject("response-body");
        ArrayNode array = body.putArray("array");
        ObjectNode responseObject = JsonNodeFactory.instance.objectNode();
        responseObject.put("response-message", notFoundResponse.getMessage());

        String responseString = response.toString();

        try {
            writer.write(responseString, 0, responseString.length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
