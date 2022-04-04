package com.unknown.supportapp.client.common.service.account;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface CheckAccountExistenceService {

    boolean check (String email) throws CustomServerError;
}
