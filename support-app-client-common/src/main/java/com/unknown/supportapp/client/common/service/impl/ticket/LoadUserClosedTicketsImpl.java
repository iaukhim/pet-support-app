package com.unknown.supportapp.client.common.service.impl.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ticket.LoadUserClosedTicketsService;
import com.unknown.supportapp.common.dto.ticket.TicketDto;

import java.util.Arrays;
import java.util.List;

public class LoadUserClosedTicketsImpl implements LoadUserClosedTicketsService {
    @Override
    public List<TicketDto> load(int userId) {
        List<TicketDto> ticketsList;

        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-user-closed-tickets", "userId", userId);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        if (responseCode != 200){
            connection.close();
            throw new RuntimeException("Server-error");
        }

        JsonNode responseBody = connection.getResponseBody();
        TicketDto[] tickets = new TicketDto[0];
        try {
            tickets = connection.getObjectMapper().readValue(responseBody.get("tickets").toString(), TicketDto[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ticketsList = Arrays.asList(tickets);

        connection.close();
        return ticketsList;
    }
}
