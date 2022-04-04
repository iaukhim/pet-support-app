package com.unknown.supportapp.client.common.service.impl.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ticket.LoadUserTicketsByEmailService;
import com.unknown.supportapp.common.dto.ticket.TicketDto;

import java.util.Arrays;
import java.util.List;

public class LoadUserTicketsByEmailImpl implements LoadUserTicketsByEmailService {

    private ExceptionHandler exceptionHandler;

    @Override
    public List<TicketDto> load(String email) throws CustomServerError {
        List<TicketDto> ticketsList;

        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("load-user-tickets-by-id", "email", email);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode >= 500){
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }

        JsonNode responseBody = connection.getResponseBody();
        TicketDto[] tickets = new TicketDto[0];
        try {
            tickets = connection.getObjectMapper().readValue(responseBody.get("tickets").toString(), TicketDto[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ticketsList = Arrays.asList(tickets);

        return ticketsList;
    }
}
