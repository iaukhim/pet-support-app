package com.unknown.supportapp.client.common.service.ticket;

import com.unknown.supportapp.common.dto.ticket.TicketDto;

import java.util.List;

public interface LoadUserClosedTicketsService {

    List<TicketDto> load (int userId);
}
