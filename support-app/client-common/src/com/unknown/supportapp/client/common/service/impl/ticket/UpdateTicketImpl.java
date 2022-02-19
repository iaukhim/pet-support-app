package com.unknown.supportapp.client.common.service.impl.ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.common.dto.ticket.TicketDto;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ticket.UpdateTicketService;

public class UpdateTicketImpl implements UpdateTicketService {
    @Override
    public void update(TicketDto ticketDto) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("update-ticket", "ticket", ticketDto);
        connection.writeRequest(request);
        connection.flush();

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        if (responseCode != 200){
            connection.close();
            throw new RuntimeException("Server-error");
        }
        connection.close();
    }
}
