package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.dao.AccountDao;
import com.unknown.supportapp.server.dao.TicketDao;
import com.unknown.supportapp.server.entities.Ticket;
import com.unknown.supportapp.common.dto.ticket.TicketDto;
import com.unknown.supportapp.server.entities.converters.TicketConverter;
import com.unknown.supportapp.server.services.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;

    private AccountDao accountDao;

    public TicketServiceImpl() {
    }

    public TicketServiceImpl(TicketDao ticketDao, AccountDao accountDao) {
        this.ticketDao = ticketDao;
        this.accountDao = accountDao;
    }

    @Override
    public List<TicketDto> loadUserTickets(int userId) {
        List<Ticket> tickets = ticketDao.loadUserTickets(userId);

        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(tickets);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserTickets(String email) {
        int id = accountDao.loadIdByEmail(email);
        List<TicketDto> ticketDtos = loadUserTickets(id);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserOpenedTickets(int userId) {
        List<Ticket> entityList = ticketDao.loadUserOpenedTickets(userId);
        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(entityList);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserClosedTickets(int userId) {
        List<Ticket> entityList = ticketDao.loadUserClosedTickets(userId);
        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(entityList);
        return ticketDtos;
    }

    @Override
    public void setManagerId(TicketDto ticketDto) {
        Ticket ticket = new TicketConverter().convertToEntity(ticketDto);
        ticketDao.setManagerId(ticket);
    }

    @Override
    public List<TicketDto> loadUnAssignedTickets() {
        List<Ticket> tickets = ticketDao.loadUnAssignedTickets();
        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(tickets);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadManagedTickets(int managerId) {
        List<Ticket> entities = ticketDao.loadManagedTickets(managerId);
        List<TicketDto> ticketDtos = new TicketConverter().convertToDtoList(entities);
        return ticketDtos;
    }

    @Override
    public void save(TicketDto ticket) {
        Ticket ticketEntity = new TicketConverter().convertToEntity(ticket);
        ticketDao.save(ticketEntity);
    }

    @Override
    public void update(TicketDto ticketDto) {
        Ticket ticket = new TicketConverter().convertToEntity(ticketDto);
        ticketDao.update(ticket);
    }

    @Override
    public void closeTicket(int id) {
        ticketDao.closeTicket(id);
    }
}
