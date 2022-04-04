package com.unknown.supportapp.client.common.service.account;

import com.unknown.supportapp.client.common.exception.CustomServerError;

public interface ChangePasswordService {
    void change(String email, String password) throws CustomServerError;
}
