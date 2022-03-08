package com.unknown.supportapp.client.common.service.account;

import com.unknown.supportapp.common.dto.acccount.AccountDto;

public interface LoadAccountByEmail {

    public AccountDto load(String email);
}
