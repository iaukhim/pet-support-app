package com.unknown.supportapp.server.controllers.impl.ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class CloseTicketController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        int id = requestBody.get("id").asInt();
        ServiceFactory.getFactory().getTicketService().closeTicket(id);
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
