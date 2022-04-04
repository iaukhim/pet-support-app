package com.unknown.supportapp.server.dao.mysql;

import com.unknown.supportapp.server.dao.ManagerDao;
import com.unknown.supportapp.server.entities.Manager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class MySqlManagerDao implements ManagerDao {

    private EntityManager entityManager;

    public MySqlManagerDao() {
    }

    public MySqlManagerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean login(Manager manager) {
        boolean result = true;

        entityManager.joinTransaction();
        Query query = entityManager.createQuery("select m  from Manager as m WHERE m.email = :email and m.password = :password", Manager.class);
        query.setParameter("email", manager.getEmail());
        query.setParameter("password", manager.getPassword());
        try {
            if(query.getResultList().isEmpty()){
                result = false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
        return result;
    }

    @Override
    public int loadIdByEmail(String email) {
        int id = -1;

        entityManager.joinTransaction();
        TypedQuery<Manager> query = entityManager.createQuery("select m from Manager as m WHERE m.email = :email", Manager.class);
        query.setParameter("email", email);
        try {
            id = query.getSingleResult().getId();
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
        return id;
    }
}
