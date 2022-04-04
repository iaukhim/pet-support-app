package com.unknown.supportapp.client.common.service.impl.ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.exception.ExceptionHandler;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ticket.UpdateTicketService;
import com.unknown.supportapp.common.dto.ticket.TicketDto;

public class UpdateTicketImpl implements UpdateTicketService {

    private ExceptionHandler exceptionHandler;

    @Override
    public void update(TicketDto ticketDto) throws CustomServerError {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("update-ticket", "ticket", ticketDto);
        connection.writeRequest(request);
        connection.flush();

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        connection.close();

        if (responseCode >= 500){
            exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleResponse(connection.getResponseBody());
        }
    }
}
