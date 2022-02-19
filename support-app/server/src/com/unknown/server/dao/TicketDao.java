package com.unknown.server.dao;

import com.unknown.server.entities.Ticket;

import java.util.List;

public interface TicketDao {

    List<Ticket> loadAll();

    List<Ticket> loadUserTickets(int userId);

    void save(Ticket ticket);

    void update(Ticket ticket);

    List<Ticket> loadUserOpenedTickets(int userId);

    List<Ticket> loadUserClosedTickets(int userId);

    List<Ticket> loadUnAssignedTickets();

    List<Ticket> loadManagedTickets(int managerId);

    void setManagerId(Ticket ticket);

    void closeTicket(int id);
}

