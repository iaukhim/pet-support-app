package com.unknown.supportapp.client.common.service.ticket;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface CloseTicketService {

    void close (int id) throws CustomServerError;
}
