package com.unknown.server.controllers.impl.ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.server.common.Response;
import com.unknown.server.common.ResponseFactory;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;

public class CloseTicketController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        int id = requestBody.get("id").asInt();
        ServiceFactory.getFactory().getTicketService().closeTicket(id);
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
