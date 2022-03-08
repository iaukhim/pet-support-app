package com.unknown.supportapp.server.services;

import com.unknown.supportapp.common.dto.acccount.AccountDto;

import java.util.List;

public interface AccountService {

    void saveAccount(AccountDto accountDto);

    void update(AccountDto accountDto);

    AccountDto loadByEmail(String email);

    List<AccountDto> loadAll();

    void delete(int id);

    boolean logIn(AccountDto accountDto);

    String registration(String email);

    boolean checkExistence(String email);

    String confirmationCode(String email);

    void changePassword(AccountDto accountDto);

    int loadIdByEmail(String email);
}
