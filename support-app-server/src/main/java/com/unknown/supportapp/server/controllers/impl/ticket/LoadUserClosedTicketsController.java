package com.unknown.supportapp.server.controllers.impl.ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.TicketService;
import com.unknown.supportapp.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.ticket.TicketDto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class LoadUserClosedTicketsController implements Controller {

    private TicketService service;

    public LoadUserClosedTicketsController() {
    }

    public LoadUserClosedTicketsController(TicketService service) {
        this.service = service;
    }

    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        int userId = requestBody.get("userId").asInt();

        List<TicketDto> ticketDtos = service.loadUserClosedTickets(userId);
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse(), "tickets", ticketDtos.toArray());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
