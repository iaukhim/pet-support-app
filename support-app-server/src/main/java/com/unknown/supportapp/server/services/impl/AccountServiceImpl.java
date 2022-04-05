package com.unknown.supportapp.server.services.impl;

import com.unknown.supportapp.server.entities.converters.AccountConverter;
import com.unknown.supportapp.server.services.AccountService;
import com.unknown.supportapp.common.dto.acccount.AccountDto;
import com.unknown.supportapp.server.dao.AccountDao;
import com.unknown.supportapp.server.entities.Account;
import com.unknown.supportapp.server.mail.MailService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    private AccountConverter accountConverter;

    public AccountServiceImpl() {
    }

    public AccountServiceImpl(AccountDao accountDao, AccountConverter accountConverter) {
        this.accountDao = accountDao;
        this.accountConverter = accountConverter;
    }

    @Override
    public void update(AccountDto accountDto) {
        Account account = accountConverter.convertToEntity(accountDto);
        accountDao.update(account);
    }

    @Override
    public void delete(int id) {
        accountDao.delete(id);
    }

    @Override
    public void saveAccount(AccountDto accountDto) {
        Account account = accountConverter.convertToEntity(accountDto);
        accountDao.save(account);
    }

    @Override
    public AccountDto loadByEmail(String email) {
        if (!checkExistence(email)){
            return null;
        }
        Account account = accountDao.loadByEmail(email);

        AccountDto accountDto = accountConverter.convertToDto(account);
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
        Account account = accountConverter.convertToEntity(accountDto);
        accountDao.changePassword(account);

    }

    @Override
    public int loadIdByEmail(String email) {
        int id = accountDao.loadIdByEmail(email);
        return id;
    }
}
