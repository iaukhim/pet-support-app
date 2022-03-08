package com.unknown.supportapp.server.controllers.impl.manager;

import com.fasterxml.jackson.databind.JsonNode;

import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class ManagerLoadIdByEmailController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        String email = requestBody.get("email").asText();
        int id = ServiceFactory.getFactory().getManagerService().loadIdByEmail(email);

        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "id", id);

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
