package com.unknown.supportapp.client.common.service.ticket;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.common.dto.ticket.TicketDto;

import java.util.List;

public interface LoadUserTicketsByEmailService {

    List<TicketDto> load(String email) throws CustomServerError;
}
