package com.unknown.supportapp.server.dao.mysql;

import com.unknown.supportapp.server.dao.AccountDao;
import com.unknown.supportapp.server.db.mysql.DbConnectionManager;
import com.unknown.supportapp.server.entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlAccountDao implements AccountDao {

    private EntityManagerFactory emf;

    public MySqlAccountDao() {

    }

    public MySqlAccountDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private final String LOG_IN = "SELECT `email`, `password` FROM pet_db.accounts WHERE `email` = ? AND `password` = ?";
    private final String LOAD_ALL = "SELECT * FROM pet_db.accounts";
    private final String LOAD_ACCOUNT_BY_EMAIL = "SELECT * FROM pet_db.accounts WHERE `email` = ?";
    private final String DELETE_ACCOUNT = "DELETE FROM pet_db.accounts WHERE `id` = ?";
    private final String SAVE_ACCOUNT = "INSERT INTO pet_db.accounts (`email`, `password`, `name`, `surname`, `phone_number`, `date_of_birth`) VALUES (?, ?, '', '', '', '2099-11-11')";
    private final String CHANGE_PASSWORD = "UPDATE pet_db.accounts SET `password` = ? WHERE `email`=?";
    private final String LOAD_ID_BY_EMAIL = "SELECT `id` FROM pet_db.accounts WHERE `email` = ?";
    private final String UPDATE = "UPDATE pet_db.accounts SET `email` = ?, `password` = ?, `name` = ?, `surname` = ?, `phone_number` = ?, `date_of_birth` = STR_TO_DATE(?, '%Y-%m-%d') WHERE `id`= ?";

    @Override
    public boolean logIn(Account account) {
        boolean result = true;

        EntityManager entityManager = emf.createEntityManager();
        entityManager.joinTransaction();
        Query query = entityManager.createQuery("select a  from Account as a WHERE a.email = :email and a.password = :password", Account.class);
        query.setParameter("email", account.getEmail());
        query.setParameter("password", account.getPassword());
        try {
            if(query.getResultList().isEmpty()){
                result = false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Dao Exciption", e);
        }
        entityManager.close();
        return result;
    }

    @Override
    public List<Account> loadAll() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.joinTransaction();
        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a", Account.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao Exciption", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Account loadByEmail(String email) {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.joinTransaction();
        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a where a.email = :email", Account.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Dao Exciption", e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void delete(int id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.joinTransaction();
        try {
            Account account = entityManager.find(Account.class, id);
            entityManager.remove(account);
        } catch (Exception e) {
            throw new RuntimeException("Dao Exciption", e);
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Account account) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.joinTransaction();

        try {
            entityManager.persist(account);
        } catch (Exception e) {
            throw new RuntimeException("Dao Exciption", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean checkAccountExistence(String email) {
        boolean result = false;

        EntityManager entityManager = emf.createEntityManager();
        entityManager.joinTransaction();
        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a where a.email = :email", Account.class);
        query.setParameter("email", email);
        try {
            if (!query.getResultList().isEmpty()) {
                result = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Dao Exciption", e);
        } finally {
            entityManager.close();
        }
        return result;
    }

    @Override
    public void changePassword(Account account) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.joinTransaction();
        Query query = entityManager.createQuery("UPDATE Account as a SET a.password = :password WHERE a.email = :email");
        query.setParameter("password", account.getPassword());
        query.setParameter("email", account.getEmail());
        try {
            query.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Dao Exciption", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int loadIdByEmail(String email) {
        int id = -1;
        EntityManager entityManager = emf.createEntityManager();
        entityManager.joinTransaction();
        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a WHERE a.email = :email", Account.class);
        query.setParameter("email", email);
        try {
            id = query.getSingleResult().getId();
        } catch (Exception e) {
            throw new RuntimeException("Dao Exciption", e);
        } finally {
            entityManager.close();
        }
        return id;
    }

    @Override
    public void update(Account account) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.joinTransaction();

        try {
            entityManager.merge(account);
        } catch (Exception e) {
            throw new RuntimeException("Dao Exciption", e);
        } finally {
            entityManager.close();
        }
    }
}
