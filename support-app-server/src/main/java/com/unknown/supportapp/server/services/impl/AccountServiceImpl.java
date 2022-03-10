package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.entities.converters.AccountConverter;
import com.unknown.supportapp.server.services.AccountService;
import com.unknown.supportapp.common.dto.acccount.AccountDto;
import com.unknown.supportapp.server.dao.AccountDao;
import com.unknown.supportapp.server.entities.Account;
import com.unknown.supportapp.server.mail.MailService;

import java.util.List;


public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public AccountServiceImpl() {
    }

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void update(AccountDto accountDto) {
        Account account = new AccountConverter().convertToEntity(accountDto);
        accountDao.update(account);
    }

    @Override
    public void delete(int id) {
        accountDao.delete(id);
    }

    @Override
    public void saveAccount(AccountDto accountDto) {
        Account account = new AccountConverter().convertToEntity(accountDto);
        accountDao.save(account);
    }

    @Override
    public AccountDto loadByEmail(String email) {
        if (!checkExistence(email)){
            return null;
        }
        Account account = accountDao.loadByEmail(email);

        AccountDto accountDto = new AccountConverter().convertToDto(account);
        return accountDto;
    }

    @Override
    public List<AccountDto> loadAll() {
        return null;
    }

    @Override
    public boolean logIn(AccountDto accountDto) {
        boolean result = accountDao.logIn(new Account(accountDto.getEmail(), accountDto.getPassword()));
        return result;
    }


    @Override
    public String registration(String email) {
        String message = MailService.getMailService().sendConfirmationEmail(email);
        return message;
    }

    @Override
    public boolean checkExistence(String email) {
        boolean result = accountDao.checkAccountExistence(email);
        return result;
    }

    @Override
    public String confirmationCode(String email) {
        String code = MailService.getMailService().sendConfirmationEmail(email);
        return code;
    }

    @Override
    public void changePassword(AccountDto accountDto) {
        Account account = new AccountConverter().convertToEntity(accountDto);
        accountDao.changePassword(account);

    }

    @Override
    public int loadIdByEmail(String email) {
        int id = accountDao.loadIdByEmail(email);
        return id;
    }
}
