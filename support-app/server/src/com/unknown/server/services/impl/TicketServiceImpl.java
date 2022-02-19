package com.unknown.server.services.impl;

import com.unknown.server.entities.Ticket;
import com.unknown.supportapp.common.dto.ticket.TicketDto;
import com.unknown.server.dao.factory.DaoFactory;
import com.unknown.server.entities.converters.TicketConverter;
import com.unknown.server.services.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    @Override
    public List<TicketDto> loadUserTickets(int userId) {
        List<Ticket> tickets = DaoFactory.getFactory().getTicketDao().loadUserTickets(userId);

        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(tickets);

        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserTickets(String email) {
        int id = DaoFactory.getFactory().getAccountDao().loadIdByEmail(email);
        List<TicketDto> ticketDtos = loadUserTickets(id);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserOpenedTickets(int userId) {
        List<Ticket> entityList = DaoFactory.getFactory().getTicketDao().loadUserOpenedTickets(userId);
        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(entityList);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserClosedTickets(int userId) {
        List<Ticket> entityList = DaoFactory.getFactory().getTicketDao().loadUserClosedTickets(userId);
        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(entityList);
        return ticketDtos;
    }

    @Override
    public void setManagerId(TicketDto ticketDto) {
        Ticket ticket = new TicketConverter().convertToEntity(ticketDto);
        DaoFactory.getFactory().getTicketDao().setManagerId(ticket);
    }

    @Override
    public List<TicketDto> loadUnAssignedTickets() {
        List<Ticket> tickets = DaoFactory.getFactory().getTicketDao().loadUnAssignedTickets();
        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(tickets);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadManagedTickets(int managerId) {
        List<Ticket> entities = DaoFactory.getFactory().getTicketDao().loadManagedTickets(managerId);
        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(entities);
        return ticketDtos;
    }

    @Override
    public void save(TicketDto ticket) {
        Ticket ticketEntity = new TicketConverter().convertToEntity(ticket);

        DaoFactory.getFactory().getTicketDao().save(ticketEntity);
    }

    @Override
    public void update(TicketDto ticketDto) {
        Ticket ticket = new TicketConverter().convertToEntity(ticketDto);

        DaoFactory.getFactory().getTicketDao().update(ticket);
    }

    @Override
    public void closeTicket(int id) {
        DaoFactory.getFactory().getTicketDao().closeTicket(id);
    }
}
