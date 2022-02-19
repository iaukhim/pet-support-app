package com.unknown.server.services;

import com.unknown.supportapp.common.dto.ticket.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> loadUserTickets(int userId);

    List<TicketDto> loadUserTickets(String email);

    List<TicketDto> loadUserOpenedTickets(int userId);

    List<TicketDto> loadUserClosedTickets(int userId);

    List<TicketDto> loadUnAssignedTickets();

    List<TicketDto> loadManagedTickets(int managerId);

    void save(TicketDto ticket);

    void update(TicketDto ticketDto);

    void closeTicket(int id);

    void setManagerId(TicketDto ticketDto);

}
