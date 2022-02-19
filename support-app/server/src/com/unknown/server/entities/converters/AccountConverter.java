package com.unknown.server.entities.converters;

import com.unknown.supportapp.common.dto.acccount.AccountDto;
import com.unknown.server.entities.Account;

public class AccountConverter {

    public AccountDto convertToDto(Account entity){
        AccountDto accountDto = new AccountDto();

        accountDto.setId(entity.getId());

        if(entity.getEmail() != null){
            accountDto.setEmail(entity.getEmail());
        }
        if(entity.getPassword() != null){
            accountDto.setPassword(entity.getPassword());
        }
        if(entity.getName() != null){
            accountDto.setName(entity.getName());
        }
        if(entity.getSurname() != null){
            accountDto.setSurname(entity.getSurname());
        }
        if(entity.getPhoneNumber() != null){
            accountDto.setPhoneNumber(entity.getPhoneNumber());
        }
        if(entity.getDateOfBirth() != null){
            accountDto.setDateOfBirth(entity.getDateOfBirth());
        }
        return accountDto;
    }

    public Account convertToEntity(AccountDto accountDto){
        Account accountEntity = new Account();

        accountEntity.setId(accountDto.getId());

        if(accountDto.getEmail() != null){
            accountEntity.setEmail(accountDto.getEmail());
        }
        if(accountDto.getPassword() != null){
            accountEntity.setPassword(accountDto.getPassword());
        }
        if(accountDto.getName() != null){
            accountEntity.setName(accountDto.getName());
        }
        if(accountDto.getSurname() != null){
            accountEntity.setSurname(accountDto.getSurname());
        }
        if(accountDto.getPhoneNumber() != null){
            accountEntity.setPhoneNumber(accountDto.getPhoneNumber());
        }
        if(accountDto.getDateOfBirth() != null){
            accountEntity.setDateOfBirth(accountDto.getDateOfBirth());
        }
        return accountEntity;
    }
}
