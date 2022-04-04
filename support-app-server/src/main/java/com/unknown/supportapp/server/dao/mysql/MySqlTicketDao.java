package com.unknown.supportapp.server.dao.mysql;

import com.unknown.supportapp.server.dao.TicketDao;
import com.unknown.supportapp.server.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

public class MySqlTicketDao implements TicketDao {

    public MySqlTicketDao() {
    }

    public MySqlTicketDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private EntityManager entityManager;

    @Override
    public List<Ticket> loadAll() {
        entityManager.joinTransaction();
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t", Ticket.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public List<Ticket> loadUserTickets(int userId) {
        entityManager.joinTransaction();
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.userId = :userId", Ticket.class);
        query.setParameter("userId", userId);

        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public void save(Ticket ticket) {
        entityManager.joinTransaction();
        try {
            entityManager.persist(ticket);
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public void update(Ticket ticket) {
        entityManager.joinTransaction();
        try {
            entityManager.merge(ticket);
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public List<Ticket> loadUserOpenedTickets(int userId) {
        entityManager.joinTransaction();
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.status = true", Ticket.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public List<Ticket> loadUserClosedTickets(int userId) {
        entityManager.joinTransaction();
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.status = false", Ticket.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public List<Ticket> loadUnAssignedTickets() {
        entityManager.joinTransaction();
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.managerId = 0", Ticket.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public List<Ticket> loadManagedTickets(int managerId) {
        entityManager.joinTransaction();
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.managerId = :managerId", Ticket.class);
        query.setParameter("managerId", managerId);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public void setManagerId(Ticket ticket) {
        update(ticket);
    }

    @Override
    public void closeTicket(int id) {
        entityManager.joinTransaction();
        Ticket ticket = entityManager.find(Ticket.class, id);
        ticket.setStatus(false);
        entityManager.merge(ticket);
    }
}
