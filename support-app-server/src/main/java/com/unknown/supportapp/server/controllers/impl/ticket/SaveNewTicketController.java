package com.unknown.supportapp.server.controllers.impl.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.services.TicketService;
import com.unknown.supportapp.server.services.factory.ServiceFactory;
import com.unknown.supportapp.common.dto.ticket.TicketDto;

import java.io.BufferedWriter;
import java.io.IOException;

public class SaveNewTicketController implements Controller {

    private TicketService service;

    public SaveNewTicketController() {
    }

    public SaveNewTicketController(TicketService service) {
        this.service = service;
    }

    @Override
    public void process(BufferedWriter writer, JsonNode requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        TicketDto ticket = new TicketDto();
        try {
            ticket = objectMapper.readValue(requestBody.get("ticket").toString(), TicketDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        service.save(ticket);
        JsonNode response = ResponseFactory.getFactory().formResponse(Response.getOkResponse());

        try {
            writer.write(response.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
