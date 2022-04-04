package com.unknown.supportapp.client.common.service.account;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.common.dto.acccount.AccountDto;

public interface LoadAccountByEmail {

    AccountDto load(String email) throws CustomServerError;
}
