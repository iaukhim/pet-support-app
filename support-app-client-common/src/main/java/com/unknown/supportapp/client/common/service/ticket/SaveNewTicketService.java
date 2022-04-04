package com.unknown.supportapp.client.common.service.ticket;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.common.dto.ticket.TicketDto;

public interface SaveNewTicketService {

    void save(TicketDto ticketDto) throws CustomServerError;
}
