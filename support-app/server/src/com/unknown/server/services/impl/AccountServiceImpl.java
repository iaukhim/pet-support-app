package com.unknown.server.services.impl;

import com.unknown.server.entities.converters.AccountConverter;
import com.unknown.server.services.AccountService;
import com.unknown.supportapp.common.dto.acccount.AccountDto;
import com.unknown.server.dao.AccountDao;
import com.unknown.server.dao.factory.DaoFactory;
import com.unknown.server.entities.Account;
import com.unknown.server.mail.MailService;

import java.util.List;


public class AccountServiceImpl implements AccountService {

    @Override
    public void update(AccountDto accountDto) {
        Account account = new AccountConverter().convertToEntity(accountDto);
        DaoFactory.getFactory().getAccountDao().update(account);
    }

    @Override
    public void delete(int id) {
        DaoFactory.getFactory().getAccountDao().delete(id);
    }

    @Override
    public void saveAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());

        DaoFactory.getFactory().getAccountDao().save(account);
    }

    @Override
    public AccountDto loadByEmail(String email) {
        Account account = DaoFactory.getFactory().getAccountDao().loadByEmail(email);

        AccountDto accountDto = new AccountConverter().convertToDto(account);
        return accountDto;
    }

    @Override
    public List<AccountDto> loadAll() {
        return null;
    }

    @Override
    public boolean logIn(AccountDto accountDto) {
        AccountDao accountDao = DaoFactory.getFactory().getAccountDao();

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
        boolean result = DaoFactory.getFactory().getAccountDao().checkAccountExistence(email);
        return result;
    }

    @Override
    public String confirmationCode(String email) {
        String code = MailService.getMailService().sendConfirmationEmail(email);
        return code;
    }

    @Override
    public void changePassword(AccountDto accountDto) {
        Account account = new Account();
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());

        DaoFactory.getFactory().getAccountDao().changePassword(account);

    }

    @Override
    public int loadIdByEmail(String email) {
        int id = DaoFactory.getFactory().getAccountDao().loadIdByEmail(email);
        return id;
    }
}
