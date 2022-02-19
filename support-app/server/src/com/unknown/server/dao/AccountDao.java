package com.unknown.server.dao;

import com.unknown.server.entities.Account;

import java.util.List;

public interface AccountDao {

    List<Account> loadAll();

    Account loadByEmail (String Email);

    void update (Account account);

    void save (Account account);

    void delete(int id);

    boolean logIn(Account account);

    boolean checkAccountExistence(String email);

    void changePassword(Account account);

    int loadIdByEmail(String email);

}
