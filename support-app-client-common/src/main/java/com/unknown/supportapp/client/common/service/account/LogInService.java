package com.unknown.supportapp.client.common.service.account;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface LogInService {

    boolean LogIn(String email, String password) throws CustomServerError;
}
