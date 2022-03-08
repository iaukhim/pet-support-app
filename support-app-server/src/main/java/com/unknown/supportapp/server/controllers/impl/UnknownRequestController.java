package com.unknown.supportapp.server.controllers.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;

import java.io.BufferedWriter;
import java.io.IOException;

public class UnknownRequestController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getNotFoundResponse());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
