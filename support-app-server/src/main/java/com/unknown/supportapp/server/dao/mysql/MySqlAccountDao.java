package com.unknown.supportapp.server.dao.mysql;

import com.unknown.supportapp.server.dao.AccountDao;
import com.unknown.supportapp.server.entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class MySqlAccountDao implements AccountDao {

    private EntityManager entityManager;

    public MySqlAccountDao() {

    }

    public MySqlAccountDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean logIn(Account account) {
        boolean result = true;

        entityManager.joinTransaction();
        Query query = entityManager.createQuery("select a  from Account as a WHERE a.email = :email and a.password = :password", Account.class);
        query.setParameter("email", account.getEmail());
        query.setParameter("password", account.getPassword());
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
    public List<Account> loadAll() {
        entityManager.joinTransaction();
        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a", Account.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
    }

    @Override
    public Account loadByEmail(String email) {
        entityManager.joinTransaction();
        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a where a.email = :email", Account.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
    }


    @Override
    public void delete(int id) {
        entityManager.joinTransaction();
        try {
            Account account = entityManager.find(Account.class, id);
            entityManager.remove(account);
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
    }

    @Override
    public void save(Account account) {
        entityManager.joinTransaction();

        try {
            entityManager.persist(account);
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
    }

    @Override
    public boolean checkAccountExistence(String email) {
        boolean result = false;

        entityManager.joinTransaction();
        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a where a.email = :email", Account.class);
        query.setParameter("email", email);
        try {
            if (!query.getResultList().isEmpty()) {
                result = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
        return result;
    }

    @Override
    public void changePassword(Account account) {
        entityManager.joinTransaction();
        Query query = entityManager.createQuery("UPDATE Account as a SET a.password = :password WHERE a.email = :email");
        query.setParameter("password", account.getPassword());
        query.setParameter("email", account.getEmail());
        try {
            query.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
    }

    @Override
    public int loadIdByEmail(String email) {
        int id = -1;

        entityManager.joinTransaction();
        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a WHERE a.email = :email", Account.class);
        query.setParameter("email", email);
        try {
            id = query.getSingleResult().getId();
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
        return id;
    }

    @Override
    public void update(Account account) {
        entityManager.joinTransaction();

        try {
            entityManager.merge(account);
        } catch (Exception e) {
            throw new RuntimeException("Dao Exception", e);
        }
    }
}
