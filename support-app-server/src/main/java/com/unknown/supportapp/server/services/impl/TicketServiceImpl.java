package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.dao.AccountDao;
import com.unknown.supportapp.server.dao.TicketDao;
import com.unknown.supportapp.server.entities.Ticket;
import com.unknown.supportapp.common.dto.ticket.TicketDto;
import com.unknown.supportapp.server.entities.converters.TicketConverter;
import com.unknown.supportapp.server.services.TicketService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;

    private AccountDao accountDao;

    private TicketConverter ticketConverter;

    public TicketServiceImpl() {
    }

    public TicketServiceImpl(TicketDao ticketDao, AccountDao accountDao, TicketConverter ticketConverter) {
        this.ticketDao = ticketDao;
        this.accountDao = accountDao;
        this.ticketConverter = ticketConverter;
    }

    @Override
    public List<TicketDto> loadUserTickets(int userId) {
        List<Ticket> tickets = ticketDao.loadUserTickets(userId);

        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(tickets);
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
        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(entityList);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserClosedTickets(int userId) {
        List<Ticket> entityList = ticketDao.loadUserClosedTickets(userId);
        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(entityList);
        return ticketDtos;
    }

    @Override
    public void setManagerId(TicketDto ticketDto) {
        Ticket ticket = ticketConverter.convertToEntity(ticketDto);
        ticketDao.setManagerId(ticket);
    }

    @Override
    public List<TicketDto> loadUnAssignedTickets() {
        List<Ticket> tickets = ticketDao.loadUnAssignedTickets();
        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(tickets);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadManagedTickets(int managerId) {
        List<Ticket> entities = ticketDao.loadManagedTickets(managerId);
        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(entities);
        return ticketDtos;
    }

    @Override
    public void save(TicketDto ticket) {
        Ticket ticketEntity = ticketConverter.convertToEntity(ticket);
        ticketDao.save(ticketEntity);
    }

    @Override
    public void update(TicketDto ticketDto) {
        Ticket ticket = ticketConverter.convertToEntity(ticketDto);
        ticketDao.update(ticket);
    }

    @Override
    public void closeTicket(int id) {
        ticketDao.closeTicket(id);
    }
}
