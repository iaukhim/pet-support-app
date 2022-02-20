package com.unknown.server.controllers.impl.ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.server.common.Response;
import com.unknown.server.common.ResponseFactory;
import com.unknown.server.controllers.Controller;
import com.unknown.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.ticket.TicketDto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class LoadUserOpenedTicketsController implements Controller {
    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        int userId = requestBody.get("userId").asInt();

        List<TicketDto> ticketDtos = ServiceFactory.getFactory().getTicketService().loadUserOpenedTickets(userId);
        Object[] array = ticketDtos.toArray();
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "tickets", array);

        try {
            writer.write(response.toString(), 0, response.toString().length());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
