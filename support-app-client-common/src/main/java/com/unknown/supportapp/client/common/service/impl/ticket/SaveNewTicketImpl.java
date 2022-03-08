package com.unknown.supportapp.client.common.service.impl.ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.client.common.net.RequestFactory;
import com.unknown.supportapp.client.common.net.ServerConnection;
import com.unknown.supportapp.client.common.service.ticket.SaveNewTicketService;
import com.unknown.supportapp.common.dto.ticket.TicketDto;


public class SaveNewTicketImpl implements SaveNewTicketService {
    @Override
    public void save(TicketDto ticketDto) {
        ServerConnection connection = new ServerConnection();

        JsonNode request = RequestFactory.getFactory().formRequest("save-new-ticket", "ticket", ticketDto);
        connection.writeRequest(request);

        JsonNode responseHeader = connection.getResponseHeader();
        int responseCode = responseHeader.get("response-code").asInt();
        if (responseCode != 200){
            connection.close();
            throw new RuntimeException("Server-error");
        }
        connection.close();
    }
}
